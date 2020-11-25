package com.javiersc.resources.networkResponse.retrofit.tests.other

import com.javiersc.resources.networkResponse.NetworkResponse
import com.javiersc.resources.networkResponse.config.models.Dog
import com.javiersc.resources.networkResponse.config.models.DogDTO
import com.javiersc.resources.networkResponse.config.models.ErrorD
import com.javiersc.resources.networkResponse.config.models.ErrorDTO
import com.javiersc.resources.networkResponse.config.models.toDog
import com.javiersc.resources.networkResponse.config.models.toErrorD
import com.javiersc.resources.networkResponse.extensions.toResource
import com.javiersc.resources.networkResponse.retrofit.tests.BaseTest
import com.javiersc.resources.resource.Resource
import io.kotest.matchers.should
import io.kotest.matchers.string.shouldContain
import io.kotest.matchers.types.beOfType
import io.kotest.matchers.types.shouldBeTypeOf
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

internal class MalformedJsonTest : BaseTest<String>() {

    override val codeToFile: Pair<Int, String?> = 200 to "malformedjson"
    override val expected = "JsonDecodingException"

    @Test
    fun `suspend call`() = runBlocking {
        val response: NetworkResponse<DogDTO, ErrorDTO> = service.getDog()
        response should beOfType<NetworkResponse.UnknownError>()
    }

    @Test
    fun `async call`() = runBlocking {
        val deferredResponse: Deferred<NetworkResponse<DogDTO, ErrorDTO>> = service.getDogAsync()
        val response: NetworkResponse<DogDTO, ErrorDTO> = deferredResponse.await()
        response should beOfType<NetworkResponse.UnknownError>()
    }

    @Test
    fun `mapping NetworkResponse to Resource`(): Unit = runBlocking {
        val resource: Resource<Dog, ErrorD> = service.getDog().toResource(
            success = DogDTO::toDog,
            error = ErrorDTO?::toErrorD,
            unknownError = Throwable::toErrorD,
            internetNotAvailable = String::toErrorD,
        )
        resource.shouldBeTypeOf<Resource.Error<ErrorD>>()
        resource.error.message shouldContain expected
    }
}
