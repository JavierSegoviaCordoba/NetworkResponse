package com.javiersc.resources.networkResponse.ktor.tests.other

import com.javiersc.resources.networkResponse.NetworkResponse
import com.javiersc.resources.networkResponse.config.models.DogDTO
import com.javiersc.resources.networkResponse.config.models.ErrorD
import com.javiersc.resources.networkResponse.config.models.ErrorDTO
import com.javiersc.resources.networkResponse.config.models.toDog
import com.javiersc.resources.networkResponse.config.models.toErrorD
import com.javiersc.resources.networkResponse.extensions.toResource
import com.javiersc.resources.networkResponse.ktor.NetworkResponse
import com.javiersc.resources.networkResponse.ktor.tests.BaseTest
import com.javiersc.resources.networkResponse.runBlocking
import com.javiersc.resources.resource.Resource
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import io.ktor.client.request.get
import kotlinx.serialization.SerializationException
import kotlin.test.Test

internal class MalformedJsonTest : BaseTest<DogDTO, ErrorDTO>() {

    override val codeToFile = 200 to "malformedjson"
    override val expected = NetworkResponse.UnknownError(SerializationException(message))

    @Test
    fun `Get 200 with a malformed json and map to Resource`() = runBlocking {
        with(NetworkResponse<DogDTO, ErrorDTO> { client.get("path") }) {
            this.shouldBeTypeOf<NetworkResponse.UnknownError>()

            toResource(
                success = DogDTO::toDog,
                error = ErrorDTO?::toErrorD,
                unknownError = Throwable::toErrorD,
                internetNotAvailable = String::toErrorD,
            ).shouldBeTypeOf<Resource.Error<ErrorD>>().error.message shouldBe expected.throwable.message
        }
    }
}

private val message =
    """|kotlinx.serialization.json.internal.JsonDecodingException: Unexpected JSON token at offset 42: Expected '}'
       |JSON input: {
       |  "id": 1,
       |  "name": "Auri",
       |  "age": 7
       |""".trimMargin()
