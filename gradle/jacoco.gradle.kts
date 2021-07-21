import com.isupatches.android.viewglu.build.Versions

plugins.apply(JacocoPlugin::class)

//configure<JacocoPluginExtension> {
//    toolVersion = Versions.JACOCO
//}

//tasks.withType(Test::class) {
//    extensions.configure<JacocoTaskExtension> {
//        excludes = listOf(
//            "**/R.class",
//            "**/R$*.class",
//            "**/BuildConfig.*",
//            "**/Manifest*.*"
//        )
//    }
//}
