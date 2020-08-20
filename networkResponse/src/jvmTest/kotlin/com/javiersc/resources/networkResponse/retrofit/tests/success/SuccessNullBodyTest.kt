package com.javiersc.resources.networkResponse.retrofit.tests.success

import com.javiersc.resources.networkResponse.NetworkResponse
import com.javiersc.resources.networkResponse.StatusCode
import com.javiersc.resources.networkResponse.extensions.toResource
import com.javiersc.resources.networkResponse.retrofit.config.models.Dog
import com.javiersc.resources.networkResponse.retrofit.config.models.DogDTO
import com.javiersc.resources.networkResponse.retrofit.config.models.ErrorD
import com.javiersc.resources.networkResponse.retrofit.config.models.ErrorDTO
import com.javiersc.resources.networkResponse.retrofit.config.models.toErrorD
import com.javiersc.resources.networkResponse.retrofit.config.models.toNullDog
import com.javiersc.resources.networkResponse.retrofit.tests.BaseNullTest
import com.javiersc.resources.resource.Resource
import io.kotest.matchers.maps.shouldContain
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.beOfType
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

internal class SuccessNullBodyTest : BaseNullTest<DogDTO?>(StatusCode.OK_200) {

    override val expectedHeader: Pair<String, List<String>> = "Content-Length" to listOf("0")

    @Test
    fun `suspend call`() = runBlocking {
        with(service.getDogWithoutBody()) {
            this should beOfType<NetworkResponse.Success<Unit>>()
            (this as NetworkResponse.Success<Unit>).headers shouldContain expectedHeader
        }
    }

    @Test
    fun `async call`() = runBlocking {
        with(service.getDogWithoutBodyAsync().await()) {
            this should beOfType<NetworkResponse.Success<Unit>>()
            (this as NetworkResponse.Success<Unit>).headers shouldContain expectedHeader
        }
    }

    @Test
    fun `mapping NetworkResponse to Resource`() = runBlocking {
        val resource: Resource<Dog, ErrorD> = service.getDogWithoutBody().toResource(
            success = ::toNullDog,
            error = ErrorDTO?::toErrorD,
            unknownError = Throwable::toErrorD,
            internetNotAvailable = String::toErrorD,
        )
        (resource as Resource.Success).data.name shouldBe "kotlin.Unit"
    }
}
