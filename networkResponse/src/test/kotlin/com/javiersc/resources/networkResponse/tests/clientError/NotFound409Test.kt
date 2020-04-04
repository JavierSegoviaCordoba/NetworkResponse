package com.javiersc.resources.networkResponse.tests.clientError

import com.javiersc.resources.networkResponse.NetworkResponse
import com.javiersc.resources.networkResponse.NetworkResponse.ClientError.Conflict
import com.javiersc.resources.networkResponse.config.models.Dog
import com.javiersc.resources.networkResponse.config.models.Error
import com.javiersc.resources.networkResponse.config.models.text
import com.javiersc.resources.networkResponse.config.models.unused
import com.javiersc.resources.networkResponse.extensions.toResource
import com.javiersc.resources.networkResponse.tests.BaseNullTest
import com.javiersc.resources.networkResponse.tests.BaseTest
import com.javiersc.resources.resource.Resource
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

internal class Conflict409Test : BaseTest<Error> {

    override val codeToFile: Pair<Int, String?> = 409 to "4xx.json"
    override val expected: Error = Error("Dog has some error")

    @Test
    fun `suspend call`() = runBlocking {
        val response: NetworkResponse<Dog, Error> = service.getDog()
        val error: Error? = (response as Conflict).error
        error shouldBe expected
    }

    @Test
    fun `async call`() = runBlocking {
        val deferredResponse: Deferred<NetworkResponse<Dog, Error>> = service.getDogAsync()
        val response: NetworkResponse<Dog, Error> = deferredResponse.await()
        val error: Error? = (response as Conflict).error
        error shouldBe expected
    }

    @Test
    fun `mapping NetworkResponse to Resource`() = runBlocking {
        val response: NetworkResponse<Dog, Error> = service.getDog()
        val resource: Resource<String, String> = response.toResource(Dog::name, Error?::text)
        val name: String? = (resource as Resource.Error).error
        name shouldBe expected.message
    }

    @Test
    fun `mapping concrete NetworkResponse to Resource`() = runBlocking {
        val response: NetworkResponse<Dog, Error> = service.getDog()
        val resource: Resource<String, String> =
            response.toResource(Dog::name, Error?::unused, mapConflict = Error?::text)
        val name: String? = (resource as Resource.Error).error
        name shouldBe expected.message
    }
}

internal class NullConflict409Test : BaseNullTest<Error>(409) {

    @Test
    fun `suspend call with null error`() = runBlocking {
        val response: NetworkResponse<Dog, Error> = service.getDog()
        val error: Error? = (response as Conflict).error
        error shouldBe null
    }
}
