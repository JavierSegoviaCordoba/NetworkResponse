package com.javiersc.resources.networkResponse.retrofit.tests.success

import com.javiersc.resources.networkResponse.NetworkResponse
import com.javiersc.resources.networkResponse.StatusCode
import com.javiersc.resources.networkResponse.extensions.toResource
import com.javiersc.resources.networkResponse.retrofit.config.models.Dog
import com.javiersc.resources.networkResponse.retrofit.config.models.DogDTO
import com.javiersc.resources.networkResponse.retrofit.config.models.ErrorD
import com.javiersc.resources.networkResponse.retrofit.config.models.ErrorDTO
import com.javiersc.resources.networkResponse.retrofit.config.models.toDefaultDog
import com.javiersc.resources.networkResponse.retrofit.config.models.toDog
import com.javiersc.resources.networkResponse.retrofit.config.models.toErrorD
import com.javiersc.resources.networkResponse.retrofit.tests.BaseNullTest
import com.javiersc.resources.resource.Resource
import io.kotest.matchers.beOfType
import io.kotest.matchers.maps.shouldContain
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

internal class Success204Test : BaseNullTest<DogDTO?>(StatusCode.NO_CONTENT_204) {

    @Test
    fun `suspend call`() = runBlocking {
        with(service.getDog()) {
            this should beOfType<NetworkResponse.Success.Empty>()
            (this as NetworkResponse.Success.Empty).headers shouldContain expectedHeader
        }
    }

    @Test
    fun `async call`() = runBlocking {
        with(service.getDogAsync().await()) {
            this should beOfType<NetworkResponse.Success.Empty>()
            (this as NetworkResponse.Success.Empty).headers shouldContain expectedHeader
        }
    }

    @Test
    fun `mapping NetworkResponse to Resource`() = runBlocking {
        val resource: Resource<Dog, ErrorD> = service.getDog().toResource(
            success = DogDTO::toDog,
            successEmpty = ::toDefaultDog,
            error = ErrorDTO?::toErrorD,
            internetNotAvailable = String::toErrorD,
        )
        (resource as Resource.Success).data.name shouldBe "Auri"
    }
}
