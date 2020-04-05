package com.javiersc.resources.networkResponse.tests.other

import com.javiersc.resources.networkResponse.NetworkResponse.CustomError
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

internal class CustomErrorTest : BaseTest<Error> {

    override val codeToFile: Pair<Int, String?> = 612 to "6xx.json"
    override val expected: Error = Error("Dog has some error")

    @Test
    fun `suspend call`() = runBlocking {
        with(service.getDog() as CustomError) {
            error shouldBe expected
            code shouldBe codeToFile.first
            headers.toHeaderList() shouldContain expectedHeader
        }
    }

    @Test
    fun `async call`() = runBlocking {
        with(service.getDogAsync().await() as CustomError) {
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
    fun `mapping concrete NetworkResponse to Resource`() = runBlocking {
        val resource: Resource<String, String> = service.getDog()
            .toResource(Dog::unused, Error?::unused, customError = Error?::text)
        (resource as Resource.Error).error shouldBe expected.message
    }
}

internal class CustomErrorNullTest : BaseNullTest<Error?>(612) {

    @Test
    fun `suspend call with null error`() = runBlocking {
        with(service.getDog() as CustomError) {
            error shouldBe expected
            code shouldBe codeToFile.first
        }
    }
}
