package com.javiersc.resources.networkResponse.tests.clientError

import com.javiersc.resources.networkResponse.NetworkResponse.ClientError.ExpectationFailed
import com.javiersc.resources.networkResponse.StatusCode
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

internal class Error417Test : BaseTest<Error> {

    override val codeToFile: Pair<Int, String?> =
        StatusCode.EXPECTATION_FAILED_417.code to "4xx.json"
    override val expected: Error = Error("Dog has some error")

    @Test
    fun `suspend call`() = runBlocking {
        with(service.getDog() as ExpectationFailed) {
            error shouldBe expected
            headers.toHeaderList() shouldContain expectedHeader
        }
    }

    @Test
    fun `async call`() = runBlocking {
        with(service.getDogAsync().await() as ExpectationFailed) {
            error shouldBe expected
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
            .toResource(Dog::unused, Error?::unused, expectationFailed = Error?::text)
        (resource as Resource.Error).error shouldBe expected.message
    }
}

internal class ErrorNull417Test : BaseNullTest<Error?>(
    StatusCode.EXPECTATION_FAILED_417.code
) {

    @Test
    fun `suspend call with null error`() = runBlocking {
        (service.getDog() as ExpectationFailed).error shouldBe expected
    }
}
