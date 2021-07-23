import com.isupatches.android.viewglu.build.Versions

plugins.apply(PmdPlugin::class)

configure<PmdExtension> {
    isConsoleOutput = false
    toolVersion = Versions.PMD
    maxFailures.set(1)
    rulesMinimumPriority.set(1)
    ruleSets = listOf("category/java/errorprone.xml", "category/java/bestpractices.xml")
}

task<Pmd>("pmd") {
    description = "Analyze code with pmd"
    group = "reporting"
    ignoreFailures = false
    source = fileTree("src/main/java")
    reports {
        xml.required.set(false)
        html.required.set(true)
    }
}
