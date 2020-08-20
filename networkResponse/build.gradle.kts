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
    maven("https://maven.pkg.jetbrains.space/kotlin/p/dokka/dev")
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
    archiveClassifier.set("dokka")
    from(tasks.dokkaHtml)
    dependsOn(tasks.dokkaHtml)
}

kotlin {
    explicitApi()

    jvm {
        mavenPublication {
            artifact(dokkaJar)
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                commonDependencies.apply {
                    implementation(kotlinSerialization)
                    implementation(coroutinesCore)
                    implementation(resource)
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
                    implementation(retrofit)
                    implementation(okHttp)
                    implementation(converterSerialization)
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
