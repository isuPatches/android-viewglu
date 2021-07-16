import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

buildscript {

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        val versions = com.isupatches.android.viewglu.build.Versions
        classpath("com.android.tools.build:gradle:${versions.AGP}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${versions.KOTLIN}")
        classpath("org.jetbrains.dokka:dokka-gradle-plugin:${versions.DOKKA}")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }

    tasks.withType(Test::class).configureEach {
        /*
        * Run tests in parallel (https://docs.gradle.org/nightly/userguide/performance.html).
        * Must be less than the number of CPU cores.
        */
        maxParallelForks = Runtime.getRuntime().availableProcessors().div(2)

        testLogging {
            // Log events
            events = setOf(
                TestLogEvent.STARTED,
                TestLogEvent.FAILED,
                TestLogEvent.SKIPPED,
                TestLogEvent.PASSED
            )

            exceptionFormat = TestExceptionFormat.FULL
            showExceptions = true
            showCauses = true
            showStackTraces = true
        }
    }
}

subprojects {
    // Static Analysis
    apply(from = "${rootProject.projectDir}/gradle/cpd.gradle.kts")
    apply(from = "${rootProject.projectDir}/gradle/detekt.gradle.kts")
    apply(from = "${rootProject.projectDir}/gradle/dexcount.gradle.kts")
    apply(from = "${rootProject.projectDir}/gradle/ktlint.gradle.kts")
    apply(from = "${rootProject.projectDir}/gradle/pmd.gradle.kts")
}
