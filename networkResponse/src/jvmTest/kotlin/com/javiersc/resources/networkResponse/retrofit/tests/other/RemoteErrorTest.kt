package com.javiersc.resources.networkResponse.retrofit.tests.other

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
import com.javiersc.resources.networkResponse.retrofit.config.service.DogService
import com.javiersc.resources.networkResponse.retrofit.tests.BaseTest
import com.javiersc.resources.resource.Resource
import io.kotest.matchers.beOfType
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import kotlin.test.Test

internal class RemoteErrorTest : BaseTest<String>() {

    override val mockWebServer: MockWebServer get() = MockWebServer()
    override val service: DogService
        get() = DogService.getService(mockWebServer.url("/"), 1L)
    override val codeToFile: Pair<Int, String?> get() = StatusCode.OK_200 to null
    override val expected: String = ""

    @Test
    fun `suspend call`() = runBlocking {
        val response: NetworkResponse<DogDTO, ErrorDTO> = service.getDog()
        response should beOfType<NetworkResponse.ServerError<ErrorDTO>>()
    }

    @Test
    fun `async call`() = runBlocking {
        val deferredResponse: Deferred<NetworkResponse<DogDTO, ErrorDTO>> = service.getDogAsync()
        val response: NetworkResponse<DogDTO, ErrorDTO> = deferredResponse.await()
        response should beOfType<NetworkResponse.ServerError<ErrorDTO>>()
    }

    @Test
    fun `mapping NetworkResponse to Resource`() = runBlocking {
        val resource: Resource<Dog, ErrorD> = service.getDog().toResource(
            success = DogDTO::toDog,
            successEmpty = ::toDefaultDog,
            error = ErrorDTO?::toErrorD,
            internetNotAvailable = String::toErrorD,
        )
        (resource as Resource.Error).error.message shouldBe expected
    }
}
