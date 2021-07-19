import com.isupatches.android.viewglu.build.BuildVersions
import com.isupatches.android.viewglu.build.Dependencies
import com.isupatches.android.viewglu.build.PublishingConstants.GROUP_ID
import com.isupatches.android.viewglu.build.lifecycle

plugins {
    id("com.android.library")
    id("com.isupatches.android.viewglue.build.plugins.BaseGradleModulePlugin")
    id("com.isupatches.android.viewglue.build.plugins.DocumentationPlugin")
    id("com.isupatches.android.viewglue.build.plugins.PublishingPlugin")
    id("kotlin-android")
    id("kotlin-kapt")
}

group = GROUP_ID
version = BuildVersions.MODULE_VERSION_NAME

android {
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(Dependencies.AndroidX.APPCOMPAT)
    lifecycle()

    implementation(Dependencies.Kotlin.STD_LIB)
}
