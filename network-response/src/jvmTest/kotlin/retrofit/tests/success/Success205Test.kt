package com.javiersc.networkResponse.retrofit.tests.success

import com.javiersc.networkResponse.NetworkResponse
import com.javiersc.networkResponse.config.models.DogDTO
import com.javiersc.networkResponse.retrofit.tests.BaseNullTest
import com.javiersc.networkResponse.runTestBlocking
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import kotlin.test.Test

internal class Success205Test : BaseNullTest<DogDTO?>(205) {

    @Test
    fun `suspend call`() = runTestBlocking {
        with(service.getDogWithoutBody()) {
            shouldBeTypeOf<NetworkResponse.Success<Unit>>()
            headers shouldBe expectedHeaders
        }
    }

    @Test
    fun `async call`() = runTestBlocking {
        with(service.getDogWithoutBodyAsync().await()) {
            shouldBeTypeOf<NetworkResponse.Success<Unit>>()
            headers shouldBe expectedHeaders
        }
    }
}
