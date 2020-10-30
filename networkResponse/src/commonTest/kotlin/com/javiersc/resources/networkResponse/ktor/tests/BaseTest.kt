package com.javiersc.resources.networkResponse.ktor.tests

import com.javiersc.resources.networkResponse.NetworkResponse
import com.javiersc.resources.networkResponse.config.extensions.readResource
import com.javiersc.resources.networkResponse.ktor.toHttpStatusCode
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.http.ContentType
import io.ktor.http.Url
import io.ktor.http.fullPath
import io.ktor.http.headersOf
import io.ktor.http.hostWithPort
import io.ktor.utils.io.errors.IOException

internal abstract class BaseTest<NR, E> {

    abstract val codeToFile: Pair<Int, String>
    abstract val expected: NetworkResponse<NR, E>

    val client = HttpClient(MockEngine) {
        install(JsonFeature) { serializer = KotlinxSerializer() }
        engine {
            addHandler { request ->
                val json = readResource(codeToFile.second)

                when (request.url.fullUrl) {
                    "http://localhost/path" -> respond(
                        content = json,
                        status = codeToFile.first.toHttpStatusCode("Custom HttpStatusCode"),
                        headers = headersOf("Content-Type" to listOf(ContentType.Application.Json.toString()))
                    )
                    "http://localhost/remote-unavailable" -> throw IOException("")
                    else -> error("Unhandled ${request.url.fullUrl}")
                }
            }
        }
    }
}

private val Url.hostWithPortIfRequired: String get() = if (port == protocol.defaultPort) host else hostWithPort
private val Url.fullUrl: String get() = "${protocol.name}://$hostWithPortIfRequired$fullPath"
