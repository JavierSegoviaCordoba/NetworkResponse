package com.javiersc.resources.networkResponse.retrofit.tests.clientError

import com.javiersc.resources.networkResponse.NetworkResponse
import com.javiersc.resources.networkResponse.config.models.Dog
import com.javiersc.resources.networkResponse.config.models.DogDTO
import com.javiersc.resources.networkResponse.config.models.ErrorD
import com.javiersc.resources.networkResponse.config.models.ErrorDTO
import com.javiersc.resources.networkResponse.config.models.internetNotAvailableToErrorD
import com.javiersc.resources.networkResponse.config.models.remoteNotAvailableToErrorD
import com.javiersc.resources.networkResponse.config.models.toDog
import com.javiersc.resources.networkResponse.config.models.toErrorD
import com.javiersc.resources.networkResponse.extensions.toResource
import com.javiersc.resources.networkResponse.retrofit.tests.BaseNullTest
import com.javiersc.resources.networkResponse.retrofit.tests.BaseTest
import com.javiersc.resources.networkResponse.runTestBlocking
import com.javiersc.resources.resource.Resource
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import kotlin.test.Test

internal class Error412Test : BaseTest<ErrorDTO>() {

    override val codeToFile: Pair<Int, String?> = 412 to "4xx.json"
    override val expected: ErrorDTO = ErrorDTO("Dog has some error")

    @Test
    fun `suspend call`() = runTestBlocking {
        with(service.getDog().shouldBeTypeOf<NetworkResponse.Error<ErrorDTO>>()) {
            error shouldBe expected
            status.value shouldBe codeToFile.first
            headers["token"] shouldBe expectedTokenHeader
        }
    }

    @Test
    fun `async call`() = runTestBlocking {
        with(service.getDogAsync().await().shouldBeTypeOf<NetworkResponse.Error<ErrorDTO>>()) {
            error shouldBe expected
            status.value shouldBe codeToFile.first
            headers["token"] shouldBe expectedTokenHeader
        }
    }

    @Test
    fun `mapping NetworkResponse to Resource`() = runTestBlocking {
        val resource: Resource<Dog, ErrorD> = service.getDog().toResource(
            success = DogDTO::toDog,
            error = ErrorDTO::toErrorD,
            unknownError = Throwable::toErrorD,
            remoteNotAvailable = ::remoteNotAvailableToErrorD,
            internetNotAvailable = ::internetNotAvailableToErrorD,
        )
        (resource as Resource.Error).error.message shouldBe expected.message
    }
}

internal class ErrorNull412Test : BaseNullTest<ErrorDTO?>(412) {

    @Test
    fun `suspend call with null error`() = runTestBlocking {
        service.getDog().shouldBeTypeOf<NetworkResponse.UnknownError>()
            .throwable.shouldBeTypeOf<ClassCastException>()
    }
}
