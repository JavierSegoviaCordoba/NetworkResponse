pluginManagement {
    repositories {
        maven("https://dl.bintray.com/kotlin/kotlin-eap")
        gradlePluginPortal()
        mavenCentral()
        jcenter()
        google()
    }
}

rootProject.name = "NetworkResponse"

enableFeaturePreview("GRADLE_METADATA")

include(":network-response")
include(":resource-extensions")
include(":docs")
