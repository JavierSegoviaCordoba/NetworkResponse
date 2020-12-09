package com.javiersc.networkResponse.ktor.tests.serverError

import com.javiersc.networkResponse.NetworkResponse
import com.javiersc.networkResponse.config.models.DogDTO
import com.javiersc.networkResponse.config.models.ErrorDTO
import com.javiersc.networkResponse.ktor.NetworkResponse
import com.javiersc.networkResponse.ktor.tests.BaseTest
import com.javiersc.networkResponse.runTestBlocking
import io.kotest.matchers.shouldBe
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import kotlin.test.Test

internal class Error505Test : BaseTest<DogDTO, ErrorDTO>() {

    override val codeToFile = 505 to "5xx.json"
    override val expected = NetworkResponse.Error(
        ErrorDTO("Dog has some error"),
        HttpStatusCode.VersionNotSupported,
        headersOf("Content-Type" to listOf("application/json"))
    )

    @Test
    fun `Request 505`() = runTestBlocking {
        NetworkResponse<DogDTO, ErrorDTO> { client.get("path") } shouldBe expected
    }
}
