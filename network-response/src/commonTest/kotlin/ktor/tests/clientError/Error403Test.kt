package com.javiersc.networkResponse.ktor.tests.clientError

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

internal class Error403Test : BaseTest<DogDTO, ErrorDTO>() {

    override val codeToFile = 403 to "4xx.json"
    override val expected = NetworkResponse.Error(
        ErrorDTO("Dog has some error"),
        HttpStatusCode.Forbidden,
        headersOf("Content-Type" to listOf("application/json"))
    )

    @Test
    fun `Request 403`() = runTestBlocking {
        NetworkResponse<DogDTO, ErrorDTO> { client.get("path") } shouldBe expected
    }
}
