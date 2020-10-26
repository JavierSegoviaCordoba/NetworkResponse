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
    archiveClassifier.set("javadoc")
    dependsOn(tasks.dokkaHtml)
    dependsOn(tasks.dokkaJavadoc)
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
                    implementation(coroutinesCore)
                    implementation(kotlinSerialization)
                    implementation(resource)
                }
            }
        }
        commonTest {
            dependencies {
                commonTestDependencies.apply {
                    implementation(coroutinesTest)
                    implementation(kotlinSerializationJson)
                    implementation(kotlinTest)
                    implementation(kotlinTestAnnotation)
                    implementation(kotestAssertions)
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
                    implementation(kotlinTestJUnit)
                    implementation(mockWebServer)
                }
            }
        }
        all {
            languageSettings.useExperimentalAnnotation("kotlin.RequiresOptIn")
        }
    }
}
