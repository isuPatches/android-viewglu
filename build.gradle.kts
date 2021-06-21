import de.aaschmid.gradle.plugins.cpd.Cpd
import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

buildscript {

    repositories {
        google()
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
    }

    dependencies {
        val versions = com.isupatches.android.viewglu.build.Versions
        classpath("com.android.tools.build:gradle:${versions.AGP}")
        classpath("de.aaschmid:gradle-cpd-plugin:${versions.CPD}")
        classpath("com.getkeepsafe.dexcount:dexcount-gradle-plugin:${versions.DEXCOUNT}")
        classpath("org.jetbrains.dokka:dokka-gradle-plugin:${versions.DOKKA}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${versions.KOTLIN}")
        classpath("org.jlleitschuh.gradle:ktlint-gradle:${versions.KTLINT_PLUGIN}")
    }
}

plugins {
    id("com.getkeepsafe.dexcount") version "2.1.0-RC01"
    id("de.aaschmid.cpd") version "3.2"
    id("io.gitlab.arturbosch.detekt") version "1.17.1"
    id("org.jlleitschuh.gradle.ktlint") version "10.0.0"
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
    apply(plugin = "cpd")
    apply(plugin = "com.getkeepsafe.dexcount")
    apply(plugin = "io.gitlab.arturbosch.detekt")
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    apply(plugin = "pmd")

    tasks.withType(Cpd::class).configureEach {
        language = "kotlin"
        group = "reporting"
        ignoreFailures = false
        source = fileTree("$projectDir/src/main/java")
        reports {
            text.required.set(false)
            xml.required.set(true)
        }
        minimumTokenCount = 50
    }

    dexcount {
        format = "tree"
        includeClasses = true
        includeFieldCount = true
        includeTotalMethodCount = true
        orderByMethodCount = true
        verbose = false
        runOnEachPackage = true
    }

    task<Pmd>("pmd") {
        description = "Analyze code with pmd"
        group = "reporting"
        ignoreFailures = false
        source = fileTree("src/main/java")
        reports {
            xml.required.set(true)
            html.required.set(true)
        }
    }

    ktlint {
        // https://github.com/pinterest/ktlint/issues/764
        // Cannot upgrade past 0.36.0 until new line issue is resolved
        version.set(com.isupatches.android.viewglu.build.Versions.KTLINT)
        debug.set(true)
        verbose.set(true)
        android.set(true)
        reporters {
            reporter(ReporterType.PLAIN)
            reporter(ReporterType.CHECKSTYLE)
        }
        ignoreFailures.set(false)
    }
}
