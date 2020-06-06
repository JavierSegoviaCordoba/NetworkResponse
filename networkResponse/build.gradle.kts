plugins {
    KotlinMultiplatform
    JaCoCo
    Detekt
    MavenPublish
    Nexus
    Dokka
}

repositories {
    maven("https://dl.bintray.com/kotlin/kotlin-eap")
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    mavenCentral()
    jcenter()
}

val networkResponseVersion: String by project
val isNetworkResponseReleaseEnv: Boolean? = System.getenv("isNetworkResponseReleaseEnv")?.toBoolean()
val isNetworkResponseRelease: String by project

val finalVersion =
    networkResponseVersion.generateVersion(isNetworkResponseReleaseEnv ?: isNetworkResponseRelease.toBoolean())

group = "com.javiersc.resources"
version = finalVersion

val dokkaJar by tasks.creating(Jar::class) {
    archiveClassifier.set("javadoc")
    from(tasks.dokka)
    dependsOn(tasks.dokka)
}

kotlin {
    jvm {
        mavenPublication {
            artifact(dokkaJar)
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
