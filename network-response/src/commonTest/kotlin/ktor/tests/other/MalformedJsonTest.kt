package com.javiersc.networkResponse.ktor.tests.other

import com.javiersc.networkResponse.NetworkResponse
import com.javiersc.networkResponse.config.models.DogDTO
import com.javiersc.networkResponse.config.models.ErrorDTO
import com.javiersc.networkResponse.ktor.NetworkResponse
import com.javiersc.networkResponse.ktor.tests.BaseTest
import com.javiersc.networkResponse.runTestBlocking
import io.kotest.matchers.types.shouldBeTypeOf
import io.ktor.client.request.get
import kotlinx.serialization.SerializationException
import kotlin.test.Test

internal class MalformedJsonTest : BaseTest<DogDTO, ErrorDTO>() {

    override val codeToFile = 200 to "malformedjson"
    override val expected = NetworkResponse.UnknownError(SerializationException(message))

    @Test
    fun `Request 200 with a malformed json`() = runTestBlocking {
        NetworkResponse<DogDTO, ErrorDTO> { client.get("path") }.shouldBeTypeOf<NetworkResponse.UnknownError>()
    }
}

private val message =
    """|kotlinx.serialization.json.internal.JsonDecodingException: Unexpected JSON token at offset 42: Expected '}'
       |JSON input: {
       |  "id": 1,
       |  "name": "Auri",
       |  "age": 7
       |""".trimMargin()
