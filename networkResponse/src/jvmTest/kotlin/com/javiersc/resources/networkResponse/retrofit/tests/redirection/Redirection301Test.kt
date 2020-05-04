package com.javiersc.resources.networkResponse.retrofit.tests.redirection

import com.javiersc.resources.networkResponse.NetworkResponse
import com.javiersc.resources.networkResponse.StatusCode
import com.javiersc.resources.networkResponse.retrofit.config.models.ErrorDTO
import com.javiersc.resources.networkResponse.retrofit.tests.BaseNullTest
import com.javiersc.resources.networkResponse.retrofit.tests.BaseTest
import io.kotest.matchers.maps.shouldContain
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.runBlocking
import kotlin.test.Test

internal class Error301Test : BaseTest<ErrorDTO>() {

    override val codeToFile: Pair<Int, String?> = StatusCode.MOVED_PERMANENTLY_301 to null
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

internal class ErrorNull301Test : BaseNullTest<ErrorDTO?>(StatusCode.MOVED_PERMANENTLY_301) {

    @Test
    fun `suspend call with null error`() = runBlocking {
        (service.getDog() as NetworkResponse.Redirection).code shouldBe codeToFile.first
    }
}
