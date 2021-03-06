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
package com.isupatches.android.viewglu.build.plugins

import com.android.build.gradle.LibraryExtension
import com.isupatches.android.viewglu.build.BuildVersions
import com.isupatches.android.viewglu.build.Dependencies
import com.isupatches.android.viewglu.build.DependencyConstants.IMPLEMENTATION
import com.isupatches.android.viewglu.build.Versions
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import java.io.File
import java.io.FileInputStream
import java.util.Locale
import java.util.Properties

class BaseGradleModulePlugin : Plugin<Project> {

    @Suppress("LongMethod")
    override fun apply(target: Project) {

        // WARNING: It is CRUCIALLY important to not just add every plugin every feature module MIGHT need. For example,
        // adding kotlin-kapt here would degrade build performance significantly. Only add those gradle-plugins that
        // EVERY feature module is CERTAIN to use.
        target.apply(plugin = "kotlin-android")

        target.configure<LibraryExtension> {

            val keystorePropertiesFile = target.file("../keystore.properties")
            val keystoreProperties = Properties()
            if (keystorePropertiesFile.exists()) {
                keystoreProperties.load(FileInputStream(keystorePropertiesFile))
            }

            compileSdk = BuildVersions.COMPILE_SDK
            buildToolsVersion = BuildVersions.BUILD_TOOLS

            defaultConfig {
                minSdk = BuildVersions.MIN_SDK
                targetSdk = BuildVersions.TARGET_SDK

                vectorDrawables.useSupportLibrary = true

                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            }

            signingConfigs {
                create("debug${target.name.capitalize(Locale.ROOT)}") {
                    storeFile = File(System.getenv("VIEWGLU_DEBUG_KEYSTORE_LOCATION") ?: keystoreProperties["viewglu.debug.keystore_location"].toString())
                    storePassword = System.getenv("VIEWGLU_DEBUG_PASSWORD") ?: keystoreProperties["viewglu.debug.password"].toString()
                    keyPassword = System.getenv("VIEWGLU_DEBUG_PASSWORD") ?: keystoreProperties["viewglu.debug.password"].toString()
                    keyAlias = System.getenv("VIEWGLU_DEBUG_KEY_ALIAS") ?: keystoreProperties["viewglu.debug.key_alias"].toString()
                }

                create("release${target.name.capitalize(Locale.ROOT)}") {
                    storeFile = File(System.getenv("VIEWGLU_RELEASE_KEYSTORE_LOCATION") ?: keystoreProperties["viewglu.release.keystore_location"].toString())
                    storePassword = System.getenv("VIEWGLU_RELEASE_PASSWORD") ?: keystoreProperties["viewglu.release.password"].toString()
                    keyPassword = System.getenv("VIEWGLU_RELEASE_PASSWORD") ?: keystoreProperties["viewglu.release.password"].toString()
                    keyAlias = System.getenv("VIEWGLU_RELEASE_KEY_ALIAS") ?: keystoreProperties["viewglu.release.key_alias"].toString()
                }
            }

            buildTypes {
                debug {
                    // Coverage needs to be disabled for publishing SNAPSHOT builds, otherwise an error
                    // will be displayed about needing to provide the original non-instrumented classes
                    isTestCoverageEnabled = false
                    isMinifyEnabled = false
                    proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
                    testProguardFile("proguard-rules-test.pro")
                    consumerProguardFile("consumer-rules.pro")
                    signingConfig = signingConfigs.getByName("debug${target.name.capitalize(Locale.ROOT)}")
                }

                release {
                    isTestCoverageEnabled = false
                    isMinifyEnabled = true
                    proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
                    consumerProguardFile("consumer-rules.pro")
                    signingConfig = signingConfigs.getByName("release${target.name.capitalize(Locale.ROOT)}")
                }
            }

            sourceSets {
                getByName("main") { jniLibs.srcDirs() }
            }

            lint {
                isCheckAllWarnings = true
                isShowAll = true
                isExplainIssues = true
                isAbortOnError = true
                isWarningsAsErrors = true
                warning("UnusedIds") // Currently warnings for ViewBinding
            }

            testOptions {
                unitTests.isReturnDefaultValues = true
            }

            testCoverage {
                jacocoVersion = Versions.JACOCO
            }
        }

        target.dependencies {
            add(IMPLEMENTATION, Dependencies.AndroidX.ANNOTATION)
            add(IMPLEMENTATION, Dependencies.Kotlin.STD_LIB)
        }
    }
}
