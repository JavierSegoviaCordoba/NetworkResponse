package com.javiersc.resources.networkResponse.tests.info

/*
import com.javiersc.resources.networkResponse.NetworkResponse
import com.javiersc.resources.networkResponse.config.models.Dog
import com.javiersc.resources.networkResponse.config.models.Error
import com.javiersc.resources.networkResponse.config.models.unused
import com.javiersc.resources.networkResponse.extensions.toResource
import com.javiersc.resources.networkResponse.tests.BaseTest
import com.javiersc.resources.resource.Resource
import io.kotest.matchers.beOfType
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

internal class Info100Test : BaseTest<Dog> {

    override val codeToFile: Pair<Int, String?> get() = 100 to null
    override val expected = Dog(0, "Roni", 4)

    @Test
    fun `suspend call`() = runBlocking {
        val response: NetworkResponse<Dog, Error> = service.getDog()
        response should beOfType<NetworkResponse.Info>()
    }

    @Test
    fun `async call`() = runBlocking {
        val deferredResponse: Deferred<NetworkResponse<Dog, Error>> = service.getDogAsync()
        val response: NetworkResponse<Dog, Error> = deferredResponse.await()
        response should beOfType<NetworkResponse.Info>()
    }

    @Test
    fun `mapping NetworkResponse to Resource`() = runBlocking {
        val response: NetworkResponse<Dog, Error> = service.getDog()
        val resource: Resource<String, String> = response.toResource(Dog::unused, Error?::unused)
        val name: String? = (resource as Resource.Success).data
        name shouldBe expected.name
    }
}
*/