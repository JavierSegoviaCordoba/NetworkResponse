package com.javiersc.resources.networkResponse.retrofit.tests.success

import com.javiersc.resources.networkResponse.NetworkResponse
import com.javiersc.resources.networkResponse.config.models.Dog
import com.javiersc.resources.networkResponse.config.models.DogDTO
import com.javiersc.resources.networkResponse.config.models.ErrorD
import com.javiersc.resources.networkResponse.config.models.ErrorDTO
import com.javiersc.resources.networkResponse.config.models.internetNotAvailableToErrorD
import com.javiersc.resources.networkResponse.config.models.remoteNotAvailableToErrorD
import com.javiersc.resources.networkResponse.config.models.toErrorD
import com.javiersc.resources.networkResponse.config.models.toNullDog
import com.javiersc.resources.networkResponse.extensions.toResource
import com.javiersc.resources.networkResponse.retrofit.tests.BaseNullTest
import com.javiersc.resources.resource.Resource
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

internal class SuccessNullBodyTest : BaseNullTest<DogDTO?>(200) {

    @Test
    fun `suspend call`() = runBlocking {
        with(service.getDogWithoutBody()) {
            shouldBeTypeOf<NetworkResponse.Success<Unit>>()
            headers shouldBe expectedHeaders
        }
    }

    @Test
    fun `async call`() = runBlocking {
        with(service.getDogWithoutBodyAsync().await()) {
            shouldBeTypeOf<NetworkResponse.Success<Unit>>()
            headers shouldBe expectedHeaders
        }
    }

    @Test
    fun `mapping NetworkResponse to Resource`() = runBlocking {
        val resource: Resource<Dog, ErrorD> = service.getDogWithoutBody().toResource(
            success = ::toNullDog,
            error = ErrorDTO?::toErrorD,
            unknownError = Throwable::toErrorD,
            remoteNotAvailable = ::remoteNotAvailableToErrorD,
            internetNotAvailable = ::internetNotAvailableToErrorD,
        )
        (resource as Resource.Success).data.name shouldBe "kotlin.Unit"
    }
}
