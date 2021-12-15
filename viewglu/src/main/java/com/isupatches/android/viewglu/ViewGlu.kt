/*
 * Copyright 2021 Patches Klinefelter
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.isupatches.android.viewglu

import android.app.Activity
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle.State.INITIALIZED
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * #### Description
 *
 * Helps to apply a [ViewBinding] to an [AppCompatActivity] by invoking the inflater.
 *
 * #### Notes
 *   - Uses the activity's layout inflater
 *   - The [LazyThreadSafetyMode] is set to NONE
 *   - Still requires the activity to apply the inflated binding
 *
 * #### Example Usage
 * <pre><code>
 *  internal class MainActivity : AppCompatActivity() {
 *      private val binding: ActivityMainBinding by paste(ActivityMainBinding::inflate)
 *
 *      override fun onCreate(savedInstanceState: Bundle?) {
 *          super.onCreate(savedInstanceState)
 *          setContentView(binding.root)
 *      }
 *  }
 * </code></pre>
 *
 * @receiver [AppCompatActivity]
 * @return Lazy<ViewBinding>
 *
 * @author Patches Klinefelter
 * @since 07/2021
 */
inline fun <VIEW_BINDING : ViewBinding> AppCompatActivity.paste(
    crossinline bindingInflater: (LayoutInflater) -> VIEW_BINDING
): Lazy<VIEW_BINDING> {
    return lazy(LazyThreadSafetyMode.NONE) {
        bindingInflater.invoke(layoutInflater)
    }
}

/**
 * #### Description
 *
 * Helps to apply a [ViewBinding] to an [Activity] by invoking the inflater.
 *
 * #### Notes
 *   - Uses the activity's layout inflater
 *   - The [LazyThreadSafetyMode] is set to NONE
 *   - Still requires the activity to apply the inflated binding
 *
 * #### Example Usage
 * <pre><code>
 *  internal class MainActivity : Activity() {
 *      private val binding: ActivityMainBinding by paste(ActivityMainBinding::inflate)
 *
 *      override fun onCreate(savedInstanceState: Bundle?) {
 *          super.onCreate(savedInstanceState)
 *          setContentView(binding.root)
 *      }
 *  }
 * </code></pre>
 *
 * @receiver [Activity]
 * @return Lazy<ViewBinding>
 *
 * @author Patches Klinefelter
 * @since 08/2021
 */
inline fun <VIEW_BINDING : ViewBinding> Activity.paste(
    crossinline bindingInflater: (LayoutInflater) -> VIEW_BINDING
): Lazy<VIEW_BINDING> {
    return lazy(LazyThreadSafetyMode.NONE) {
        bindingInflater.invoke(layoutInflater)
    }
}

/**
 * #### Description
 *
 * Helps manage the [ViewBinding] for a [Fragment] so that it is properly destroyed and not leaked.
 *
 * #### Notes
 *
 *  - Attaches a lifecycle observer to the receiving fragment
 *  - Requires the fragment to set the binding value
 *  - Removes the binding when the Fragment's onDestroy is triggered
 *  - Throws and error when binding is accessed before onCreateView or after onDestroy
 *
 * #### Example Usage
 *
 * <pre><code>
 *  internal class FragmentInflated : BaseFragment() {
 *      private var binding: FragmentWithTextBinding by paste()
 *
 *      override fun onCreateView(
 *          inflater: LayoutInflater,
 *          container: ViewGroup?,
 *          savedInstanceState: Bundle?
 *      ): View {
 *          binding = FragmentWithTextBinding.inflate(inflater, container, false)
 *          return binding.root
 *      }
 *  }
 * </code></pre>
 *
 * @receiver [Fragment]
 * @return ReadWriteProperty<Fragment, ViewBinding>
 * @throws [error] When binding is accessed before onCreateView or after onDestroy
 *
 * @author Patches Klinefelter
 * @since 07/2021
 */
fun <VIEW_BINDING> Fragment.paste(): ReadWriteProperty<Fragment, VIEW_BINDING> {
    return object : ReadWriteProperty<Fragment, VIEW_BINDING>, DefaultLifecycleObserver {

        private var binding: VIEW_BINDING? = null
        private val mainHandler = Handler(Looper.getMainLooper())

        init {
            this@paste
                .viewLifecycleOwnerLiveData
                .observe(
                    this@paste,
                    { owner: LifecycleOwner? ->
                        owner?.lifecycle?.addObserver(this)
                    }
                )
        }

        override fun onDestroy(owner: LifecycleOwner) {
            super.onDestroy(owner)
            owner.lifecycle.removeObserver(this)

            // Post to main thread is required so that the Fragment can use the binding
            // for cleanup in onDestroy and onDestroyView prior to it being released
            mainHandler.post {
                binding = null
            }
        }

        override fun getValue(thisRef: Fragment, property: KProperty<*>): VIEW_BINDING {
            return binding ?: error("Called before onCreateView or after onDestroy.")
        }

        override fun setValue(thisRef: Fragment, property: KProperty<*>, value: VIEW_BINDING) {
            binding = value
        }
    }
}

/**
 * #### Description
 *
 * Helps manage the [ViewBinding] for a [Fragment] so that it is properly destroyed and not leaked.
 *
 * #### Notes
 *
 *  - Attaches a lifecycle observer to the receiving fragment
 *  - Requires the fragment to pass in the view to be be bound
 *  - Removes the binding when the Fragment's onDestroy is triggered
 *  - Access binding if at least initialized, otherwise attempts to create binding
 *  - Releases binding onDestroy by posting to main thread
 *
 * #### Example Usage
 *
 * <pre><code>
 *  internal class FragmentBound : BaseFragmentWithLayout(R.layout.fragment_with_text) {
 *      override val binding: FragmentWithTextBinding by paste(FragmentWithTextBinding::bind)
 *  }
 * </code></pre>
 *
 * @receiver [Fragment]
 * @return ReadOnlyProperty<Fragment, ViewBinding>
 *
 * @author Patches Klinefelter
 * @since 12/2021
 */
fun <VIEW_BINDING : ViewBinding> Fragment.paste(
    viewBinder: (View) -> VIEW_BINDING
): ReadOnlyProperty<Fragment, VIEW_BINDING> {

    return object : ReadOnlyProperty<Fragment, VIEW_BINDING>, DefaultLifecycleObserver {

        private var binding: VIEW_BINDING? = null
        private val mainHandler = Handler(Looper.getMainLooper())

        override fun onDestroy(owner: LifecycleOwner) {
            super.onDestroy(owner)
            owner.lifecycle.removeObserver(this)

            // Post to main thread is required so that the Fragment can use the binding
            // for cleanup in onDestroy and onDestroyView prior to it being released
            mainHandler.post {
                binding = null
            }
        }

        @MainThread
        override fun getValue(thisRef: Fragment, property: KProperty<*>): VIEW_BINDING {
            return binding ?: viewBinder(requireView()).also {
                if (viewLifecycleOwner.lifecycle.currentState.isAtLeast(INITIALIZED)) {
                    viewLifecycleOwner.lifecycle.addObserver(this)
                    binding = it
                }
            }
        }
    }
}
