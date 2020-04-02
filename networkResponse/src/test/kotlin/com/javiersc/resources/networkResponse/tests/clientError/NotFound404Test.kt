package com.javiersc.resources.networkResponse.tests.clientError

import com.javiersc.resources.networkResponse.NetworkResponse
import com.javiersc.resources.networkResponse.NetworkResponse.ClientError.NotFound
import com.javiersc.resources.networkResponse.config.models.Dog
import com.javiersc.resources.networkResponse.config.models.Error
import com.javiersc.resources.networkResponse.config.models.text
import com.javiersc.resources.networkResponse.extensions.toResource
import com.javiersc.resources.networkResponse.tests.BaseNullTest
import com.javiersc.resources.networkResponse.tests.BaseTest
import com.javiersc.resources.resource.Resource
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

internal class NotFound404Test : BaseTest<Error> {

    override val codeToFile: Pair<Int, String?> = 404 to "404.json"
    override val expected: Error = Error("Dog not found")

    @Test
    fun `suspend call`() = runBlocking {
        val response: NetworkResponse<Dog, Error> = service.getDog()
        val error: Error? = (response as NotFound).error
        error shouldBe expected
    }

    @Test
    fun `async call`() = runBlocking {
        val deferredResponse: Deferred<NetworkResponse<Dog, Error>> = service.getDogAsync()
        val response: NetworkResponse<Dog, Error> = deferredResponse.await()
        val error: Error? = (response as NotFound).error
        error shouldBe expected
    }

    @Test
    fun `mapping NetworkResponse to Resource`() = runBlocking {
        val response: NetworkResponse<Dog, Error> = service.getDog()
        val resource: Resource<String, String> = response.toResource(Dog::name, Error?::text)
        val name: String? = (resource as Resource.Error).error
        name shouldBe expected.message
    }
}

internal class NullNotFound404Test : BaseNullTest<Error>(404) {

    @Test
    fun `suspend call with null error`() = runBlocking {
        val response: NetworkResponse<Dog, Error> = service.getDog()
        val error: Error? = (response as NotFound).error
        error shouldBe null
    }
}
