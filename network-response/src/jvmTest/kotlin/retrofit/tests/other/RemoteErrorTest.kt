package com.javiersc.resources.networkResponse.retrofit.tests.other

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
import com.javiersc.resources.networkResponse.retrofit.config.service.DogService
import com.javiersc.resources.networkResponse.retrofit.tests.BaseTest
import com.javiersc.resources.networkResponse.runTestBlocking
import com.javiersc.resources.resource.Resource
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.beOfType
import kotlinx.coroutines.Deferred
import okhttp3.mockwebserver.MockWebServer
import kotlin.test.Test

internal class RemoteErrorTest : BaseTest<String>() {

    override val mockWebServer: MockWebServer get() = MockWebServer()
    override val service: DogService get() = DogService.getService(mockWebServer.url("/"), 1L)
    override val codeToFile: Pair<Int, String?> get() = 200 to null
    override val expected: String = ""

    @Test
    fun `suspend call`() = runTestBlocking {
        val response: NetworkResponse<DogDTO, ErrorDTO> = service.getDog()
        response should beOfType<NetworkResponse.RemoteNotAvailable>()
    }

    @Test
    fun `async call`() = runTestBlocking {
        val deferredResponse: Deferred<NetworkResponse<DogDTO, ErrorDTO>> = service.getDogAsync()
        val response: NetworkResponse<DogDTO, ErrorDTO> = deferredResponse.await()
        response should beOfType<NetworkResponse.RemoteNotAvailable>()
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
        (resource as Resource.Error).error.message shouldBe "No remote"
    }
}
