plugins {
    id(Plugins.Kotlin.jvm)
    id(Plugins.Kotlin.kotlinSerialization)
    BintraySetup
    jacoco
}

repositories {
    jcenter()
}

tasks {
    test {
        useJUnit()
        useJUnitPlatform()
        testLogging {
            setExceptionFormat("full")
            events("passed", "skipped", "failed")
        }
    }
    jacocoTestReport {
        executionData(
            fileTree(project.rootDir.absolutePath).include("**/build/jacoco/*.exec")
        )

        reports {
            xml.isEnabled = true
            xml.destination = file("$buildDir/reports/jacoco/report.xml")
            html.isEnabled = false
            csv.isEnabled = false
        }
    }
}

dependencies {
    implementation(Dependencies.Kotlin.stdlib)
    implementation(Dependencies.KotlinX.Coroutines.core)
    implementation(Dependencies.KotlinX.Coroutines.test)
    implementation(Dependencies.Retrofit2.retrofit)
    implementation(Dependencies.KotlinX.serialization)

    testImplementation(Dependencies.Retrofit2.retrofit)
    testImplementation(Dependencies.Retrofit2.retrofitMock)
    testImplementation(Dependencies.Retrofit2.converterSerialization)
    testImplementation(Dependencies.OkHttp.loggingInterceptor)
    testImplementation(Dependencies.OkHttp.mockWebServer)

    api(Dependencies.resource)

    testImplementation(Dependencies.jUnit)
    testRuntimeOnly(Dependencies.jUnitEngine)

    testImplementation(Dependencies.Kotest.junit)
    testImplementation(Dependencies.Kotest.assertions)

    testImplementation(Dependencies.mockito)
    testImplementation(Dependencies.truth)
    testImplementation(Dependencies.guava)
}
