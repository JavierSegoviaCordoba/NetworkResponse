package com.javiersc.resources.networkResponse.tests.success

import com.javiersc.resources.networkResponse.NetworkResponse
import com.javiersc.resources.networkResponse.NetworkResponse.Success.AlreadyReported
import com.javiersc.resources.networkResponse.StatusCode
import com.javiersc.resources.networkResponse.config.models.Dog
import com.javiersc.resources.networkResponse.config.models.Error
import com.javiersc.resources.networkResponse.config.models.unused
import com.javiersc.resources.networkResponse.extensions.toResource
import com.javiersc.resources.networkResponse.tests.BaseTest
import com.javiersc.resources.resource.Resource
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

internal class Success208Test : BaseTest<Dog> {

    override val codeToFile: Pair<Int, String?>
        get() = StatusCode.ALREADY_REPORTED_208 to "208.json"
    override val expected = Dog(0, "Roni", 4)

    @Test
    fun `suspend call`() = runBlocking {
        val response: NetworkResponse<Dog, Error> = service.getDog()
        val dog: Dog = (response as AlreadyReported).value
        dog shouldBe expected
    }

    @Test
    fun `async call`() = runBlocking {
        val deferredResponse: Deferred<NetworkResponse<Dog, Error>> = service.getDogAsync()
        val response: NetworkResponse<Dog, Error> = deferredResponse.await()
        val dog: Dog = (response as AlreadyReported).value
        dog shouldBe expected
    }

    @Test
    fun `mapping NetworkResponse to Resource`() = runBlocking {
        val response: NetworkResponse<Dog, Error> = service.getDog()
        val resource: Resource<String, String> = response.toResource(Dog::name, Error?::unused)
        val name: String? = (resource as Resource.Success).data
        name shouldBe expected.name
    }
}
