package com.javiersc.resources.networkResponse.ktor.tests.clientError

import com.javiersc.resources.networkResponse.NetworkResponse
import com.javiersc.resources.networkResponse.config.extensions.runBlocking
import com.javiersc.resources.networkResponse.config.models.DogDTO
import com.javiersc.resources.networkResponse.config.models.ErrorD
import com.javiersc.resources.networkResponse.config.models.ErrorDTO
import com.javiersc.resources.networkResponse.config.models.toDog
import com.javiersc.resources.networkResponse.config.models.toErrorD
import com.javiersc.resources.networkResponse.extensions.toResource
import com.javiersc.resources.networkResponse.ktor.NetworkResponse
import com.javiersc.resources.networkResponse.ktor.tests.BaseTest
import com.javiersc.resources.resource.Resource
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import kotlin.test.Test

internal class Error401Test : BaseTest<DogDTO, ErrorDTO>() {

    override val codeToFile = 401 to "4xx.json"
    override val expected = NetworkResponse.Error(
        ErrorDTO("Dog has some error"),
        HttpStatusCode.Unauthorized,
        headersOf("Content-Type" to listOf("application/json"))
    )

    @Test
    fun `Get 401 and map to Resource`() = runBlocking {
        with(NetworkResponse<DogDTO, ErrorDTO> { client.get("path") }) {
            this shouldBe expected

            toResource(
                success = DogDTO::toDog,
                error = ErrorDTO?::toErrorD,
                unknownError = Throwable::toErrorD,
                internetNotAvailable = String::toErrorD,
            ).shouldBeTypeOf<Resource.Error<ErrorD>>().error.message shouldBe expected.error?.message
        }
    }
}
