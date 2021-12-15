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

@Suppress("StringLiteralDuplication")
object Versions {
    // Core tooling
    const val AGP: String = "7.0.4"
    const val KOTLIN: String = "1.6.10"

    // AndroidX
    const val ANDROIDX_ANNOTATION: String = "1.3.0"
    const val ANDROIDX_APPCOMPAT: String = "1.4.0"
    const val ANDROIDX_CONSTRAINT_LAYOUT: String = "2.1.2"
    const val ANDROIDX_LIFECYCLE: String = "2.4.0"
    const val ANDROIDX_NAVIGATION: String = "2.3.5"
    const val ANDROIDX_VIEWPAGER_2: String = "1.0.0"

    // Google
    const val GOOGLE_MATERIAL: String = "1.4.0"

    // Static Analysis
    const val CPD: String = "3.2"
    const val DETEKT: String = "1.19.0"
    const val DEXCOUNT: String = "3.0.1"
    const val KTLINT_PLUGIN: String = "10.2.0"
    const val KTLINT: String = "0.43.2"

    // Documentation
    const val DOKKA: String = "1.5.0"

    // Code Coverage
    const val JACOCO: String = "0.8.7"

    // Common Test
    const val ANDROIDX_TEST_EXT_JUNIT: String = "1.1.2"
    const val ANDROIDX_FRAGMENT_TESTING: String = "1.4.0"

    // Unit Test
    const val JUNIT: String = "4.13.2"

    // Android Test
    const val ANDROIDX_ESPRESSO: String = "3.4.0"
    const val ANDROIDX_TEST_RULES: String = "1.4.0"
    const val ANDROIDX_TEST_RUNNER: String = "1.4.0"
}
