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

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import androidx.viewbinding.ViewBinding
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
 *     private val binding: ActivityMainBinding by paste(ActivityMainBinding::inflate)
 *
 *     override fun onCreate(savedInstanceState: Bundle?) {
 *         super.onCreate(savedInstanceState)
 *         setContentView(binding.root)
 *     }
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
 *     private var binding: FragmentMainBinding by paste()
 *
 *     override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
 *         binding = FragmentMainBinding.inflate(inflater, container, false)
 *         return binding.root
 *     }
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
    return object : ReadWriteProperty<Fragment, VIEW_BINDING>, LifecycleObserver {

        private var binding: VIEW_BINDING? = null

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

        @Suppress("unused")
        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun onDestroy() {
            binding = null
        }

        override fun getValue(thisRef: Fragment, property: KProperty<*>): VIEW_BINDING {
            return binding ?: error("Called before onCreateView or after onDestroy.")
        }

        override fun setValue(thisRef: Fragment, property: KProperty<*>, value: VIEW_BINDING) {
            binding = value
        }
    }
}
