import com.getkeepsafe.dexcount.DexCountExtension
import com.getkeepsafe.dexcount.DexMethodCountPlugin
import com.getkeepsafe.dexcount.OutputFormat

buildscript {
    repositories {
        mavenCentral()
    }

    dependencies {
        val versions = com.isupatches.android.viewglu.build.Versions
        classpath("com.getkeepsafe.dexcount:dexcount-gradle-plugin:${versions.DEXCOUNT}")
    }
}

plugins.apply(DexMethodCountPlugin::class)

configure<DexCountExtension> {
    format.set(OutputFormat.TREE)
    includeClasses.set(true)
    includeFieldCount.set(true)
    includeTotalMethodCount.set(true)
    orderByMethodCount.set(true)
    verbose.set(false)
    runOnEachPackage.set(true)
}
