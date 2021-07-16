import com.isupatches.android.viewglu.build.BuildVersions
import com.isupatches.android.viewglu.build.Dependencies
import com.isupatches.android.viewglu.build.PublishingConstants.GROUP_ID
import com.isupatches.android.viewglu.build.lifecycle
import org.jetbrains.dokka.Platform.jvm
import org.jetbrains.dokka.gradle.DokkaTask

plugins {
    id("com.android.library")
    id("com.isupatches.android.viewglue.build.plugins.BaseGradleModulePlugin")
    id("kotlin-android")
    id("org.jetbrains.dokka")
}

apply(from = "${rootProject.projectDir}/gradle/publish.gradle.kts")

tasks.withType<DokkaTask>().configureEach {
    outputDirectory.set(rootProject.projectDir.resolve("documentation"))
    moduleName.set(project.name)
    suppressObviousFunctions.set(false)
    dokkaSourceSets {
        configureEach {
            offlineMode.set(false)
            includeNonPublic.set(true)
            skipDeprecated.set(false)
            reportUndocumented.set(true)
            skipEmptyPackages.set(false)
            platform.set(jvm)
            jdkVersion.set(11)
            noStdlibLink.set(false)
            noJdkLink.set(false)
            noAndroidSdkLink.set(false)
        }
    }
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
