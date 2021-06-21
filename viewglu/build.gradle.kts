import com.isupatches.android.viewglu.build.lifecycle

plugins {
    id("com.android.library")
    id("com.isupatches.android.viewglue.build.plugins.BaseGradleModulePlugin")
    id("kotlin-android")
    id("org.jetbrains.dokka")
}

tasks.dokka {
    outputFormat = "gfm"
    outputDirectory = "${rootProject.projectDir}/documentation"
    configuration {
        moduleName = project.name
        skipEmptyPackages = true
    }
}

android {
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(com.isupatches.android.viewglu.build.Dependencies.AndroidX.APPCOMPAT)
    lifecycle()

    implementation(com.isupatches.android.viewglu.build.Dependencies.Kotlin.STD_LIB)
}
