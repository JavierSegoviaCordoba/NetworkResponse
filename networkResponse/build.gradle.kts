plugins {
    id(Plugins.Kotlin.multiplatform)
    id(Plugins.Kotlin.kotlinSerialization)
    JaCoCo
    Detekt
    MavenPublish
    Nexus
}

repositories {
    maven("https://dl.bintray.com/kotlin/kotlin-eap")
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    mavenCentral()
    jcenter()
}

val networkResponseVersion: String by project
val isNetworkResponseReleaseEnv: Boolean? =
    System.getenv("isNetworkResponseReleaseEnv")?.toBoolean()
val isNetworkResponseRelease: String by project

val finalVersion = networkResponseVersion
    .generateVersion(isNetworkResponseReleaseEnv ?: isNetworkResponseRelease.toBoolean())

group = "com.javiersc.resources"
version = finalVersion

val javaDocs by tasks.creating(Jar::class) {
    dependsOn("javadocJar")
    archiveClassifier.set("javadoc")
}

kotlin {
    jvm {
        mavenPublication {
            artifact(javaDocs)
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                commonDependencies.apply {
                    api(kotlinStdlib)
                    api(kotlinSerialization)
                    api(coroutinesCore)
                    api(resource)
                }
            }
        }
        commonTest {
            dependencies {
                commonTestDependencies.apply {
                    implementation(kotlinTest)
                    implementation(kotlinTestAnnotation)
                }
            }
        }

        val jvmMain by getting {
            dependencies {
                jvmDependencies.apply {
                    api(kotlinStdlib)
                    api(kotlinSerialization)
                    api(coroutinesCore)
                    api(retrofit)
                    api(okHttp)
                    api(kotlinSerialization)
                    api(converterSerialization)
                }
            }
        }
        val jvmTest by getting {
            dependencies {
                jvmTestDependencies.apply {
                    implementation(kotlinTest)
                    implementation(kotlinTestJUnit)
                    implementation(mockWebServer)
                    implementation(kotestJunit)
                    implementation(kotestAssertions)
                }
            }
        }
        all {
            languageSettings.useExperimentalAnnotation("kotlin.RequiresOptIn")
        }
    }
}
