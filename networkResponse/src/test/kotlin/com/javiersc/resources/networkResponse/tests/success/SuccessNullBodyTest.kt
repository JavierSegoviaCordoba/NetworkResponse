package com.javiersc.resources.networkResponse.tests.success

import com.javiersc.resources.networkResponse.NetworkResponse.Success.NoContent
import com.javiersc.resources.networkResponse.StatusCode
import com.javiersc.resources.networkResponse.config.models.Dog
import com.javiersc.resources.networkResponse.config.models.Error
import com.javiersc.resources.networkResponse.config.models.unused
import com.javiersc.resources.networkResponse.extensions.toResource
import com.javiersc.resources.networkResponse.tests.BaseNullTest
import com.javiersc.resources.resource.Resource
import io.kotest.matchers.beOfType
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.runBlocking
import okhttp3.internal.http2.Header
import okhttp3.internal.toHeaderList
import org.junit.jupiter.api.Test

internal class SuccessNullBodyTest : BaseNullTest<Dog>(StatusCode.OK_200.code) {

    @Test
    fun `suspend call`() = runBlocking {
        with(service.getDog()) {
            this should beOfType<NoContent<Dog>>()
            (this as NoContent).headers.toHeaderList() shouldContain Header("Content-Length", "0")
        }
    }

    @Test
    fun `async call`() = runBlocking {
        with(service.getDogAsync().await()) {
            this should beOfType<NoContent<Dog>>()
            (this as NoContent).headers.toHeaderList() shouldContain Header("Content-Length", "0")
        }
    }

    @Test
    fun `mapping NetworkResponse to Resource`() = runBlocking {
        val resource: Resource<String, String> =
            service.getDog().toResource(Dog::name, Error?::unused)
        (resource as Resource.Success).data shouldBe null
    }
}
