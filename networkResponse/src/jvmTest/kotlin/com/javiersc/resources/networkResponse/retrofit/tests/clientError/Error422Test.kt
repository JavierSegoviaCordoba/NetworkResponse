package com.javiersc.resources.networkResponse.retrofit.tests.clientError

import com.javiersc.resources.networkResponse.NetworkResponse
import com.javiersc.resources.networkResponse.StatusCode
import com.javiersc.resources.networkResponse.extensions.toResource
import com.javiersc.resources.networkResponse.retrofit.config.models.Dog
import com.javiersc.resources.networkResponse.retrofit.config.models.DogDTO
import com.javiersc.resources.networkResponse.retrofit.config.models.ErrorD
import com.javiersc.resources.networkResponse.retrofit.config.models.ErrorDTO
import com.javiersc.resources.networkResponse.retrofit.config.models.toDog
import com.javiersc.resources.networkResponse.retrofit.config.models.toErrorD
import com.javiersc.resources.networkResponse.retrofit.tests.BaseNullTest
import com.javiersc.resources.networkResponse.retrofit.tests.BaseTest
import com.javiersc.resources.resource.Resource
import io.kotest.matchers.maps.shouldContain
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

internal class Error422Test : BaseTest<ErrorDTO>() {

    override val codeToFile: Pair<Int, String?> = StatusCode.UNPROCESSABLE_ENTITY_422 to "4xx.json"
    override val expected: ErrorDTO = ErrorDTO("Dog has some error")

    @Test
    fun `suspend call`() = runBlocking {
        with(service.getDog() as NetworkResponse.Error) {
            error shouldBe expected
            code shouldBe codeToFile.first
            headers shouldContain expectedHeader
        }
    }

    @Test
    fun `async call`() = runBlocking {
        with(service.getDogAsync().await() as NetworkResponse.Error) {
            error shouldBe expected
            code shouldBe codeToFile.first
            headers shouldContain expectedHeader
        }
    }

    @Test
    fun `mapping NetworkResponse to Resource`() = runBlocking {
        val resource: Resource<Dog, ErrorD> = service.getDog().toResource(
            success = DogDTO::toDog,
            error = ErrorDTO?::toErrorD,
            internetNotAvailable = String::toErrorD,
        )
        (resource as Resource.Error).error.message shouldBe expected.message
    }
}

internal class ErrorNull422Test : BaseNullTest<ErrorDTO?>(StatusCode.UNPROCESSABLE_ENTITY_422) {

    @Test
    fun `suspend call with null error`() = runBlocking {
        (service.getDog() as NetworkResponse.Error).error shouldBe null
    }
}
