package com.javiersc.networkResponse.ktor.tests.success

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

internal class Success201Test : BaseTest<DogDTO, ErrorDTO>() {

    override val codeToFile = 201 to "2xx.json"
    override val expected = NetworkResponse.Success(
        DogDTO(1, "Auri", 7),
        HttpStatusCode.Created,
        headersOf("Content-Type" to listOf("application/json"))
    )

    @Test
    fun `Request 201`() = runTestBlocking {
        NetworkResponse<DogDTO, ErrorDTO> { client.get("path") } shouldBe expected
    }
}
