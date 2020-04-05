package com.javiersc.resources.networkResponse.tests.serverError

import com.javiersc.resources.networkResponse.NetworkResponse.ServerError.Custom
import com.javiersc.resources.networkResponse.config.models.Dog
import com.javiersc.resources.networkResponse.config.models.Error
import com.javiersc.resources.networkResponse.config.models.text
import com.javiersc.resources.networkResponse.config.models.unused
import com.javiersc.resources.networkResponse.extensions.toResource
import com.javiersc.resources.networkResponse.tests.BaseNullTest
import com.javiersc.resources.networkResponse.tests.BaseTest
import com.javiersc.resources.resource.Resource
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.runBlocking
import okhttp3.internal.toHeaderList
import org.junit.jupiter.api.Test

internal class Error5xxTest : BaseTest<Error> {

    override val codeToFile: Pair<Int, String?> = 554 to "5xx.json"
    override val expected: Error = Error("Dog has some error")

    @Test
    fun `suspend call`() = runBlocking {
        with(service.getDog() as Custom) {
            error shouldBe expected
            code shouldBe codeToFile.first
            headers.toHeaderList() shouldContain expectedHeader
        }
    }

    @Test
    fun `async call`() = runBlocking {
        with(service.getDogAsync().await() as Custom) {
            error shouldBe expected
            code shouldBe codeToFile.first
            headers.toHeaderList() shouldContain expectedHeader
        }
    }

    @Test
    fun `mapping NetworkResponse to Resource`() = runBlocking {
        val resource: Resource<String, String> =
            service.getDog().toResource(Dog::unused, Error?::text)
        (resource as Resource.Error).error shouldBe expected.message
    }

    @Test
    fun `mapping concrete custom NetworkResponse to Resource`() = runBlocking {
        val resource: Resource<String, String> = service.getDog()
            .toResource(Dog::unused, Error?::unused, customServerError = Error?::text)
        (resource as Resource.Error).error shouldBe expected.message
    }
}

internal class ErrorNull5xxTest : BaseNullTest<Error?>(554) {

    @Test
    fun `suspend call with null error`() = runBlocking {
        with(service.getDog() as Custom) {
            error shouldBe expected
            code shouldBe codeToFile.first
        }
    }
}
