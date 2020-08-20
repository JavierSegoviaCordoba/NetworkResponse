val commonDependencies get() = Dependencies.Common
val commonTestDependencies get() = Dependencies.CommonTest
val jvmDependencies get() = Dependencies.Jvm
val jvmTestDependencies get() = Dependencies.JvmTest

object Dependencies {

    object Common {
        val coroutinesCore = kotlinx("coroutines-core:${versions.coroutines}")
        val kotlinSerialization = kotlinx("serialization-core:${versions.serialization}")
        const val resource = "com.javiersc.resources:resource:${versions.resource}"
    }

    object CommonTest {
        val kotlinTest = kotlin("test-common")
        val kotlinTestAnnotation = kotlin("test-annotations-common")
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
        const val kotestJunit = "io.kotest:kotest-runner-junit5-jvm:${versions.kotest}"
        const val kotestAssertions = "io.kotest:kotest-assertions-core-jvm:${versions.kotest}"
    }

    const val detektFormatting = "io.gitlab.arturbosch.detekt:detekt-formatting:${versions.detekt}"
}

private fun kotlin(dependency: String) = "org.jetbrains.kotlin:kotlin-$dependency"
private fun kotlinx(dependency: String) = "org.jetbrains.kotlinx:kotlinx-$dependency"
