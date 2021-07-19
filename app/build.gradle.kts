import com.isupatches.android.viewglu.build.Dependencies
import com.isupatches.android.viewglu.build.debug
import com.isupatches.android.viewglu.build.navigation
import com.isupatches.android.viewglu.build.release
import java.util.Properties

plugins {
    id("com.android.application")
    id("kotlin-android")
}

apply(from = "${rootProject.projectDir}/gradle/jacoco.gradle.kts")

val keystoreProperties: Properties = Properties()
val keystoreFile: File = rootProject.file("keystore.properties")
if (keystoreFile.exists()) {
    keystoreFile.inputStream().use { keystoreProperties.load(it) }
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

    signingConfigs {
        getByName("debug") {
            storeFile = file(keystoreProperties.getProperty("app.debug.keystore_location"))
            keyAlias = keystoreProperties.getProperty("app.debug.key_alias")
            storePassword = System.getenv("APP_DEBUG_PASSWORD") ?: keystoreProperties.getProperty("app.debug.password")
            keyPassword = System.getenv("APP_DEBUG_PASSWORD") ?: keystoreProperties.getProperty("app.debug.password")
        }

        create("release") {
            storeFile = file(keystoreProperties.getProperty("app.release.keystore_location"))
            keyAlias = keystoreProperties.getProperty("app.release.key_alias")
            storePassword = keystoreProperties.getProperty("app.release.password")
            keyPassword = keystoreProperties.getProperty("app.release.password")
        }
    }

    buildTypes {
        debug {
            applicationIdSuffix = ".debug"
            isTestCoverageEnabled = false
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules-debug.pro")
            testProguardFile(file("proguard-rules-test.pro"))
            signingConfig = signingConfigs.getByName("debug")
        }

        release {
            isTestCoverageEnabled = false
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules-release.pro")
            signingConfig = signingConfigs.getByName("release")
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
    implementation("com.isupatches.android:viewglu:0.1.0-SNAPSHOT") {
        isChanging = true
    }

    implementation(Dependencies.AndroidX.APPCOMPAT)
    implementation(Dependencies.AndroidX.CONSTRAINT_LAYOUT)

    implementation(Dependencies.Kotlin.STD_LIB)

    implementation(Dependencies.Google.MATERIAL)
    navigation()
}
