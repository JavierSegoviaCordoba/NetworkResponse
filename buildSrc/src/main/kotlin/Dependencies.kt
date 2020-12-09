val commonDependencies get() = Dependencies.Common
val commonTestDependencies get() = Dependencies.CommonTest
val jvmDependencies get() = Dependencies.Jvm
val jvmTestDependencies get() = Dependencies.JvmTest

object Dependencies {

    object Common {
        val coroutinesCore = kotlinx("coroutines-core:${versions.coroutines}")
        val ktorCore = ktor("client-core:${versions.ktor}")
        val ktorJson = ktor("client-json:${versions.ktor}")
        val ktorSerialization = ktor("client-serialization:${versions.ktor}")
        const val resource = "com.javiersc.resources:resource:${versions.resource}"
    }

    object CommonTest {
        val coroutinesTest = kotlinx("coroutines-test:${versions.coroutines}")
        val kotlinSerializationJson = kotlinx("serialization-json:${versions.serialization}")
        val kotlinTest = kotlin("test-common")
        val kotlinTestAnnotation = kotlin("test-annotations-common")
        const val kotestAssertions = "io.kotest:kotest-assertions-core:${versions.kotest}"
        val ktorMock = ktor("client-mock:${versions.ktor}")
    }

    object Jvm {
        const val retrofit = "com.squareup.retrofit2:retrofit:${versions.retrofit}"
        const val okHttp = "com.squareup.okhttp3:okhttp:${versions.okHttp}"
        const val converterSerialization =
            "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${versions.retrofitConverter}"
    }

    object JvmTest {
        val kotlinTest = kotlin("test")
        val kotlinTestJUnit = kotlin("test-junit")
        const val mockWebServer = "com.squareup.okhttp3:mockwebserver:${versions.okHttp}"
    }

    const val detektFormatting = "io.gitlab.arturbosch.detekt:detekt-formatting:${versions.detekt}"
}

private fun kotlin(dependency: String) = "org.jetbrains.kotlin:kotlin-$dependency"
private fun kotlinx(dependency: String) = "org.jetbrains.kotlinx:kotlinx-$dependency"
private fun ktor(dependency: String) = "io.ktor:ktor-$dependency"
