import com.isupatches.android.viewglu.build.Dependencies
import com.isupatches.android.viewglu.build.debug
import com.isupatches.android.viewglu.build.navigation
import com.isupatches.android.viewglu.build.release

plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    compileSdkVersion(com.isupatches.android.viewglu.build.BuildVersions.COMPILE_SDK)
    buildToolsVersion(com.isupatches.android.viewglu.build.BuildVersions.BUILD_TOOLS)

    defaultConfig {
        applicationId = "com.isupatches.android.viewbinding.delegates.sample"

        minSdkVersion(com.isupatches.android.viewglu.build.BuildVersions.MIN_SDK)
        targetSdkVersion(com.isupatches.android.viewglu.build.BuildVersions.TARGET_SDK)

        versionCode = com.isupatches.android.viewglu.build.BuildVersions.MODULE_VERSION_CODE
        versionName = com.isupatches.android.viewglu.build.BuildVersions.MODULE_VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            applicationIdSuffix = ".debug"
            isTestCoverageEnabled = false
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules-debug.pro")
            testProguardFile(file("proguard-rules-test.pro"))
            // todo@sign
        }

        release {
            isTestCoverageEnabled = false
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules-release.pro")
            // todo@sign
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "${JavaVersion.VERSION_11}"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":viewglu"))

    implementation(Dependencies.AndroidX.APPCOMPAT)
    implementation(Dependencies.AndroidX.CONSTRAINT_LAYOUT)

    implementation(Dependencies.Kotlin.STD_LIB)

    implementation(Dependencies.Google.MATERIAL)
    navigation()
}
