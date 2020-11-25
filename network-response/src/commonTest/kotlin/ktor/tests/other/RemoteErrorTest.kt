package com.javiersc.resources.networkResponse.ktor.tests.other

import com.javiersc.resources.networkResponse.NetworkResponse
import com.javiersc.resources.networkResponse.config.models.DogDTO
import com.javiersc.resources.networkResponse.config.models.ErrorD
import com.javiersc.resources.networkResponse.config.models.ErrorDTO
import com.javiersc.resources.networkResponse.config.models.toDog
import com.javiersc.resources.networkResponse.config.models.toErrorD
import com.javiersc.resources.networkResponse.extensions.toResource
import com.javiersc.resources.networkResponse.ktor.NetworkResponse
import com.javiersc.resources.networkResponse.ktor.emptyHeader
import com.javiersc.resources.networkResponse.ktor.tests.BaseTest
import com.javiersc.resources.networkResponse.runBlocking
import com.javiersc.resources.networkResponse.utils.Constants.HttpStatusCodeRemoteUnavailable
import com.javiersc.resources.resource.Resource
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import io.ktor.client.request.get
import kotlin.test.Test

internal class RemoteErrorTest : BaseTest<DogDTO, ErrorDTO>() {

    override val codeToFile = 200 to "malformedjson"
    override val expected = NetworkResponse.Error(null, HttpStatusCodeRemoteUnavailable, emptyHeader)

    @Test
    fun `Get 200 with a malformed json and map to Resource`() = runBlocking {
        with(NetworkResponse<DogDTO, ErrorDTO> { client.get("remote-unavailable") }) {
            this.shouldBeTypeOf<NetworkResponse.Error<ErrorDTO>>()

            toResource(
                success = DogDTO::toDog,
                error = ErrorDTO?::toErrorD,
                unknownError = Throwable::toErrorD,
                internetNotAvailable = String::toErrorD,
            ).shouldBeTypeOf<Resource.Error<ErrorD>>().error.code shouldBe expected.status.value
        }
    }
}
