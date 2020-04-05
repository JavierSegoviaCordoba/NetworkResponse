package com.javiersc.resources.networkResponse.tests.clientError

import com.javiersc.resources.networkResponse.NetworkResponse.ClientError.MethodNotAllowed
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

internal class Error405Test : BaseTest<Error> {

    override val codeToFile: Pair<Int, String?> =
        StatusCode.METHOD_NOT_ALLOWED_405.code to "4xx.json"
    override val expected: Error = Error("Dog has some error")

    @Test
    fun `suspend call`() = runBlocking {
        with(service.getDog() as MethodNotAllowed) {
            error shouldBe expected
            headers.toHeaderList() shouldContain expectedHeader
        }
    }

    @Test
    fun `async call`() = runBlocking {
        with(service.getDogAsync().await() as MethodNotAllowed) {
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
        val resource: Resource<String, String> =
            service.getDog()
                .toResource(Dog::unused, Error?::unused, methodNotAllowed = Error?::text)
        (resource as Resource.Error).error shouldBe expected.message
    }
}

internal class NullMethodNotAllowed405Test : BaseNullTest<Error?>(
    StatusCode.METHOD_NOT_ALLOWED_405.code
) {

    @Test
    fun `suspend call with null error`() = runBlocking {
        (service.getDog() as MethodNotAllowed).error shouldBe expected
    }
}