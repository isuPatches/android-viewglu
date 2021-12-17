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
package com.isupatches.android.viewglu.activity

import android.app.Activity
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding

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
