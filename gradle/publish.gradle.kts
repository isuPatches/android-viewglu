import com.isupatches.android.viewglu.build.PublishingConstants.GROUP_ID
import org.gradle.api.publish.PublishingExtension
import java.io.File
import java.net.URI
import java.util.Properties

apply(plugin = "maven-publish")
apply(plugin = "signing")

val localProperties: Properties = Properties()
val localFile: File = rootProject.file("local.properties")
if (localFile.exists()) {
    localFile.inputStream().use { localProperties.load(it) }
}

configure<PublishingExtension> {
//    publications {
//        create<MavenPublication>("debug") {
//            groupId = GROUP_ID
//            artifactId = project.name
//            version = project.version.toString()
//
//            println("Components: $components")
//            from(components["debug"])
//
//            artifact(tasks["sourcesJar"])
////                artifact(kdocJar)
//        }
//
//        create<MavenPublication>("release") {
//            groupId = GROUP_ID
//            artifactId = project.name
//            version = project.version.toString()
//
//            from(components["release"])
//
//            artifact(tasks["sourcesJar"])
////                artifact(kdocJar)
//        }
//
//        repositories {
//            maven {
//                name = "Release"
//                url = URI("https://oss.sonatype.org/service/local/staging/deploy/maven2")
//                credentials {
//                    username = System.getenv("SONATYPE_USERNAME") ?: localProperties["sonatypeUsername"].toString()
//                    password = System.getenv("SONATYPE_PASSWORD") ?: localProperties["sonatypePassword"].toString()
//                }
//            }
//
//            maven {
//                name = "Snapshot"
//                url = URI("https://oss.sonatype.org/content/repositories/snapshots")
//                credentials {
//                    username = System.getenv("SONATYPE_USERNAME") ?: localProperties["sonatypeUsername"].toString()
//                    password = System.getenv("SONATYPE_PASSWORD") ?: localProperties["sonatypePassword"].toString()
//                }
//            }
//        }
//
//        configure<SigningExtension> {
//            sign(publications["debug"])
//            sign(publications["release"])
//        }
//    }
}

tasks.create<Jar>("sourcesJar") {
    outputs.upToDateWhen { false }
    archiveClassifier.set("sources")
//    from(java.sourceSets["main"].java.srcDirs)
}

extra["signing.keyId"] = System.getenv("SIGNING_KEY_ID") ?: localProperties["signing.keyId"].toString()
extra["signing.password"] = System.getenv("SIGNING_PASSWORD") ?: localProperties["signing.password"].toString()
extra["signing.secretKeyRingFile"] = System.getenv("SIGNING_KEY_RING_FILE") ?: localProperties[
    "signing.secretKeyRingFile"
].toString()
