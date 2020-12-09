package com.javiersc.networkResponse.retrofit.tests.other

import com.javiersc.networkResponse.NetworkResponse
import com.javiersc.networkResponse.config.models.DogDTO
import com.javiersc.networkResponse.config.models.ErrorDTO
import com.javiersc.networkResponse.retrofit.tests.BaseTest
import com.javiersc.networkResponse.runTestBlocking
import io.kotest.matchers.should
import io.kotest.matchers.types.beOfType
import kotlinx.coroutines.Deferred
import kotlin.test.Test

internal class MalformedJsonTest : BaseTest<String>() {

    override val codeToFile: Pair<Int, String?> = 200 to "malformedjson"
    override val expected = "JsonDecodingException"

    @Test
    fun `suspend call`() = runTestBlocking {
        val response: NetworkResponse<DogDTO, ErrorDTO> = service.getDog()
        response should beOfType<NetworkResponse.UnknownError>()
    }

    @Test
    fun `async call`() = runTestBlocking {
        val deferredResponse: Deferred<NetworkResponse<DogDTO, ErrorDTO>> = service.getDogAsync()
        val response: NetworkResponse<DogDTO, ErrorDTO> = deferredResponse.await()
        response should beOfType<NetworkResponse.UnknownError>()
    }
}
