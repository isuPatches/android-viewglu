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
package com.isupatches.android.viewglu.build

object Dependencies {

    object AndroidX {
        const val ANNOTATION: String = "androidx.annotation:annotation:${Versions.ANDROIDX_ANNOTATION}"
        const val APPCOMPAT: String = "androidx.appcompat:appcompat:${Versions.ANDROIDX_APPCOMPAT}"
        const val CONSTRAINT_LAYOUT: String = "androidx.constraintlayout:constraintlayout:" +
            Versions.ANDROIDX_CONSTRAINT_LAYOUT

        object Lifecycle {
            const val RUNTIME: String = "androidx.lifecycle:lifecycle-runtime:${Versions.ANDROIDX_LIFECYCLE}"
            const val COMPILER: String = "androidx.lifecycle:lifecycle-compiler:${Versions.ANDROIDX_LIFECYCLE}"
        }

        object Navigation {
            const val FRAGMENT = "androidx.navigation:navigation-fragment:${Versions.ANDROIDX_NAVIGATION}"
            const val UI ="androidx.navigation:navigation-ui:${Versions.ANDROIDX_NAVIGATION}"
        }
    }

    object Kotlin {
        const val STD_LIB: String = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.KOTLIN}"
    }

    object Google {
        const val MATERIAL: String = "com.google.android.material:material:${Versions.GOOGLE_MATERIAL}"
    }
}
