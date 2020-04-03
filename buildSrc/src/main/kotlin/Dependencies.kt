object Dependencies {
    object Kotlin {
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8"
        const val reflect = "org.jetbrains.kotlin:kotlin-reflect"
    }

    object KotlinX {
        object Coroutines {
            const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
            const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
        }

        const val serialization =
            "org.jetbrains.kotlinx:kotlinx-serialization-runtime:${Versions.serialization}"
    }

    object Retrofit2 {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val retrofitMock = "com.squareup.retrofit2:retrofit-mock:${Versions.retrofit}"
        const val converterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
        const val converterSerialization =
            "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.5.0"
    }

    object OkHttp {
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
        const val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Versions.okHttp}"
        const val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
    }

    const val resource = "com.javiersc.resources:resource:${Versions.resource}"

    const val jUnit = "org.junit.jupiter:junit-jupiter-api:${Versions.jUnit}"
    const val jUnitEngine = "org.junit.jupiter:junit-jupiter-engine:${Versions.jUnit}"

    const val mockito = "org.mockito:mockito-core:${Versions.mockito}"

    object Kotest {
        const val junit = "io.kotest:kotest-runner-junit5-jvm:${Versions.kotest}"
        const val assertions = "io.kotest:kotest-assertions-core-jvm:${Versions.kotest}"
    }

    const val truth = "com.google.truth:truth:${Versions.truth}"
    const val guava = "com.google.guava:guava:${Versions.guava}"

    const val detektFormatting = "io.gitlab.arturbosch.detekt:detekt-formatting:${Versions.detekt}"
}