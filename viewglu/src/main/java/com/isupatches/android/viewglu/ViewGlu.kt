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
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

inline fun <VIEW_BINDING : ViewBinding> AppCompatActivity.paste(
    crossinline bindingInflater: (LayoutInflater) -> VIEW_BINDING
): Lazy<VIEW_BINDING> {
    return lazy(LazyThreadSafetyMode.NONE) {
        bindingInflater.invoke(layoutInflater)
    }
}

fun <VIEW_BINDING> Fragment.paste(): ReadWriteProperty<Fragment, VIEW_BINDING> {
    return object : ReadWriteProperty<Fragment, VIEW_BINDING>, DefaultLifecycleObserver {

        private var binding: VIEW_BINDING? = null

        init {
            this@paste
                .viewLifecycleOwnerLiveData
                .observe(this@paste, { owner: LifecycleOwner? ->
                    owner?.lifecycle?.addObserver(this)
                })
        }

        override fun onDestroy(owner: LifecycleOwner) {
            binding = null
        }

        override fun getValue(thisRef: Fragment, property: KProperty<*>): VIEW_BINDING {
            return binding ?: error("Called before onCreateView or after onDestroyView.")
        }

        override fun setValue(thisRef: Fragment, property: KProperty<*>, value: VIEW_BINDING) {
            binding = value
        }
    }
}
