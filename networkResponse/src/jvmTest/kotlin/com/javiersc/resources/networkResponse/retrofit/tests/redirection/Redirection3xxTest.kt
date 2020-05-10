package com.javiersc.resources.networkResponse.retrofit.tests.redirection

import com.javiersc.resources.networkResponse.NetworkResponse
import com.javiersc.resources.networkResponse.retrofit.config.models.ErrorDTO
import com.javiersc.resources.networkResponse.retrofit.tests.BaseNullTest
import com.javiersc.resources.networkResponse.retrofit.tests.BaseTest
import io.kotest.matchers.maps.shouldContain
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

internal class Error3xxTest : BaseTest<ErrorDTO>() {

    override val codeToFile: Pair<Int, String?> = RANDOM_REDIRECTION to null
    override val expected: ErrorDTO = ErrorDTO("Dog has some error")

    @Test
    fun `suspend call`() = runBlocking {
        with(service.getDog() as NetworkResponse.Redirection) {
            code shouldBe codeToFile.first
            headers shouldContain expectedHeader
        }
    }

    @Test
    fun `async call`() = runBlocking {
        with(service.getDogAsync().await() as NetworkResponse.Redirection) {
            code shouldBe codeToFile.first
            headers shouldContain expectedHeader
        }
    }
}

internal class ErrorNull3xxTest : BaseNullTest<ErrorDTO?>(RANDOM_REDIRECTION) {

    @Test
    fun `suspend call with null error`() = runBlocking {
        (service.getDog() as NetworkResponse.Redirection).code shouldBe codeToFile.first
    }
}

private const val RANDOM_REDIRECTION = 386
