plugins {
    id("maven-publish")
    `java-library`
    signing
}

java {
    withJavadocJar()
}

signing {
    useGpgCmd()
    sign(publishing.publications)
}

val isNetworkResponseRelease: String by project

publishing {
    publications.withType<MavenPublication> {
        pom {
            name.set("NetworkResponse")
            description.set("NetworkResponse multiplatform")
            url.set("http://github.com/JavierSegoviaCordoba/NetworkResponse")
            licenses {
                license {
                    name.set("The Apache License, Version 2.0")
                    url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                }
            }
            developers {
                developer {
                    id.set("JavierSegoviaCordoba")
                    name.set("Javier Segovia Cordoba")
                    email.set("javiersegoviacordoba@gmail.com")
                }
            }
            scm {
                url.set("https://github.com/JavierSegoviaCordoba/NetworkResponse")
                connection.set("scm:git:https://github.com/JavierSegoviaCordoba/NetworkResponse.git")
                developerConnection.set("scm:git:git@github.com:JavierSegoviaCordoba/NetworkResponse.git")
            }
        }
        repositories {
            val releasesRepo = "https://oss.sonatype.org/service/local/staging/deploy/maven2"
            val snapshotsRepo = "https://oss.sonatype.org/content/repositories/snapshots"

            maven(if (isNetworkResponseRelease.toBoolean()) releasesRepo else snapshotsRepo) {
                credentials {
                    username = System.getenv("ossUser")
                    password = System.getenv("ossToken")
                }
            }
        }
    }
}
