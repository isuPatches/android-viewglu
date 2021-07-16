import org.jlleitschuh.gradle.ktlint.KtlintExtension
import org.jlleitschuh.gradle.ktlint.KtlintPlugin
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType.CHECKSTYLE
import org.jlleitschuh.gradle.ktlint.reporter.ReporterType.PLAIN

buildscript {
    repositories {
        mavenCentral()
        maven("https://plugins.gradle.org/m2/")
    }

    dependencies {
        val versions = com.isupatches.android.viewglu.build.Versions
        classpath("org.jlleitschuh.gradle:ktlint-gradle:${versions.KTLINT_PLUGIN}")
    }
}

plugins.apply(KtlintPlugin::class)

configure<KtlintExtension> {
    // https://github.com/pinterest/ktlint/issues/764
    // Cannot upgrade past 0.36.0 until new line issue is resolved
    version.set(com.isupatches.android.viewglu.build.Versions.KTLINT)
    debug.set(true)
    verbose.set(true)
    android.set(true)
    reporters {
        reporter(PLAIN)
        reporter(CHECKSTYLE)
    }
    ignoreFailures.set(false)
}
