package com.javiersc.networkResponse.retrofit.tests.clientError

import com.javiersc.networkResponse.NetworkResponse
import com.javiersc.networkResponse.config.models.ErrorDTO
import com.javiersc.networkResponse.retrofit.tests.BaseNullTest
import com.javiersc.networkResponse.retrofit.tests.BaseTest
import com.javiersc.networkResponse.runTestBlocking
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import kotlin.test.Test

internal class Error415Test : BaseTest<ErrorDTO>() {

    override val codeToFile: Pair<Int, String?> =
        415 to "4xx.json"
    override val expected: ErrorDTO = ErrorDTO("Dog has some error")

    @Test
    fun `suspend call`() = runTestBlocking {
        with(service.getDog().shouldBeTypeOf<NetworkResponse.Error<ErrorDTO>>()) {
            error shouldBe expected
            status.value shouldBe codeToFile.first
            headers["token"] shouldBe expectedTokenHeader
        }
    }

    @Test
    fun `async call`() = runTestBlocking {
        with(service.getDogAsync().await().shouldBeTypeOf<NetworkResponse.Error<ErrorDTO>>()) {
            error shouldBe expected
            status.value shouldBe codeToFile.first
            headers["token"] shouldBe expectedTokenHeader
        }
    }
}

internal class ErrorNull415Test : BaseNullTest<ErrorDTO?>(415) {

    @Test
    fun `suspend call with null error`() = runTestBlocking {
        service.getDog().shouldBeTypeOf<NetworkResponse.UnknownError>()
            .throwable.shouldBeTypeOf<ClassCastException>()
    }
}
