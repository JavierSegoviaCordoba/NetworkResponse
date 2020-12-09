package com.javiersc.networkResponse.retrofit.tests.other

import com.javiersc.networkResponse.NetworkResponse
import com.javiersc.networkResponse.config.models.DogDTO
import com.javiersc.networkResponse.config.models.ErrorDTO
import com.javiersc.networkResponse.retrofit.config.service.DogService
import com.javiersc.networkResponse.retrofit.tests.BaseTest
import com.javiersc.networkResponse.runTestBlocking
import io.kotest.matchers.should
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
}
