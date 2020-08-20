pluginManagement {
    repositories {
        maven("https://dl.bintray.com/kotlin/kotlin-eap")
        maven("https://maven.pkg.jetbrains.space/kotlin/p/dokka/dev")
        gradlePluginPortal()
        mavenCentral()
        jcenter()
        google()
    }

    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "com.android.library", "com.android.application" -> {
                    useModule("com.android.tools.build:gradle:${requested.version}")
                }
                "kotlinx-serialization" -> {
                    useModule("org.jetbrains.kotlin:kotlin-serialization:${requested.version}")
                }
                "io.gitlab.arturbosch.detekt" -> {
                    useModule("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${requested.version}")
                }
            }
        }
    }
}

rootProject.name = "NetworkResponse"

enableFeaturePreview("GRADLE_METADATA")

include(":networkResponse").also { project(":networkResponse").name = "network-response" }
include(":docs")
