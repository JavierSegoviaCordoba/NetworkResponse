plugins {
    KotlinMultiplatform
    JaCoCo
    NexusPublish
    Dokka
    NetworkResponseVersioning
}

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
                    api(project(":network-response"))
                    api(logger)
                }
            }
        }

        commonTest {
            dependencies {
                commonTestDependencies.apply {
                    implementation(kotlinTest)
                    implementation(kotlinTestAnnotation)
                    implementation(kotestAssertions)
                }
            }
        }

        named("jvmTest") {
            dependencies {
                jvmTestDependencies.apply {
                    implementation(kotlinTestJUnit)
                }
            }
        }

        defaultLanguageSettings
    }
}
