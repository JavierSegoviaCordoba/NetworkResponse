val commonDependencies get() = Dependencies.Common
val commonTestDependencies get() = Dependencies.CommonTest
val jvmDependencies get() = Dependencies.Jvm
val jvmTestDependencies get() = Dependencies.JvmTest

object Dependencies {

    object Common {
        val kotlinStdlib = kotlin("stdlib-common")
        val coroutinesCore = kotlinx("coroutines-core-common:${Versions.coroutines}")
        val kotlinSerialization = kotlinx("serialization-runtime-common:${Versions.serialization}")
        const val resource = "com.javiersc.resources:resource:${Versions.resource}"
    }

    object CommonTest {
        val kotlinTest = kotlin("test-common")
        val kotlinTestAnnotation = kotlin("test-annotations-common")
    }

    object Jvm {
        val kotlinStdlib = kotlin("stdlib-jdk8")
        val coroutinesCore = kotlinx("coroutines-core:${Versions.coroutines}")
        val kotlinSerialization = kotlinx("serialization-runtime:${Versions.serialization}")
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
        const val converterSerialization =
            "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.5.0"
    }

    object JvmTest {
        val kotlinTest = kotlin("test")
        val kotlinTestJUnit = kotlin("test-junit")
        const val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.okHttp}"
        const val kotestJunit = "io.kotest:kotest-runner-junit5-jvm:${Versions.kotest}"
        const val kotestAssertions = "io.kotest:kotest-assertions-core-jvm:${Versions.kotest}"
    }

    const val detektFormatting = "io.gitlab.arturbosch.detekt:detekt-formatting:${Versions.detekt}"
}

private fun kotlin(dependency: String) = "org.jetbrains.kotlin:kotlin-$dependency"
private fun kotlinx(dependency: String) = "org.jetbrains.kotlinx:kotlinx-$dependency"
