dependencyResolutionManagement {
    repositories {
        gradlePluginPortal()
        maven("https://dl.bintray.com/kotlin/kotlin-eap")
        mavenCentral()
        jcenter()
        google()
        maven("https://maven.pkg.jetbrains.space/kotlin/p/dokka/dev")
        maven("https://oss.sonatype.org/content/repositories/snapshots")
    }
}

rootProject.name = "NetworkResponse"

enableFeaturePreview("GRADLE_METADATA")

include(":network-response")
include(":resource-extensions")
include(":docs")
