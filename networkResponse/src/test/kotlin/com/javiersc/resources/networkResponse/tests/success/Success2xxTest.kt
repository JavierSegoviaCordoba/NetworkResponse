package com.javiersc.resources.networkResponse.tests.success

import com.javiersc.resources.networkResponse.NetworkResponse.Success.Custom
import com.javiersc.resources.networkResponse.config.models.Dog
import com.javiersc.resources.networkResponse.config.models.Error
import com.javiersc.resources.networkResponse.config.models.unused
import com.javiersc.resources.networkResponse.extensions.toResource
import com.javiersc.resources.networkResponse.tests.BaseTest
import com.javiersc.resources.resource.Resource
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.runBlocking
import okhttp3.internal.toHeaderList
import org.junit.jupiter.api.Test

internal class Success2xxTest : BaseTest<Dog> {

    override val codeToFile: Pair<Int, String?> get() = 276 to "2xx.json"
    override val expected = Dog(1, "Auri", 7)

    @Test
    fun `suspend call`() = runBlocking {
        with(service.getDog() as Custom) {
            value shouldBe expected
            headers!!.toHeaderList() shouldContain expectedHeader
            code shouldBe 276
        }
    }

    @Test
    fun `async call`() = runBlocking {
        with(service.getDogAsync().await() as Custom) {
            value shouldBe expected
            headers!!.toHeaderList() shouldContain expectedHeader
            code shouldBe 276
        }
    }

    @Test
    fun `mapping NetworkResponse to Resource`() = runBlocking {
        val resource: Resource<String, String> =
            service.getDog().toResource(Dog::name, Error?::unused)
        (resource as Resource.Success).data shouldBe expected.name
    }
}
