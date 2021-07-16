import de.aaschmid.gradle.plugins.cpd.Cpd
import de.aaschmid.gradle.plugins.cpd.CpdExtension
import de.aaschmid.gradle.plugins.cpd.CpdPlugin

buildscript {
    repositories {
        mavenCentral()
    }

    dependencies {
        val versions = com.isupatches.android.viewglu.build.Versions
        classpath("de.aaschmid:gradle-cpd-plugin:${versions.CPD}")
    }
}

plugins.apply(CpdPlugin::class)

configure<CpdExtension> {
    language = "kotlin"
    group = "reporting"
    isIgnoreFailures = false
    minimumTokenCount = 50
}

tasks.withType<Cpd>().configureEach {
    source = fileTree("$projectDir/src/main/java")
    reports {
        text.required.set(false)
        xml.required.set(true)
    }
}
