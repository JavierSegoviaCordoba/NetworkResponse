package com.javiersc.networkResponse.retrofit.tests

import com.javiersc.networkResponse.retrofit.config.mockResponse
import com.javiersc.networkResponse.retrofit.config.service.DogService
import io.ktor.http.Headers
import io.ktor.http.headersOf
import okhttp3.mockwebserver.MockWebServer
import kotlin.test.AfterTest
import kotlin.test.BeforeTest

internal abstract class BaseTest<T> {

    internal open val mockWebServer = MockWebServer()
    internal open val service: DogService get() = DogService.getService(mockWebServer.url("/"))
    abstract val codeToFile: Pair<Int, String?>
    abstract val expected: T
    val expectedTokenHeader: String get() = "1234"

    @BeforeTest
    fun `start mockWebServer`() {
        mockWebServer.start()
    }

    @BeforeTest
    fun `enqueue response`() {
        mockWebServer.enqueue(mockResponse(codeToFile))
    }

    @AfterTest
    fun `close mockWebServer`() {
        mockWebServer.shutdown()
    }
}

internal open class BaseNullTest<T>(private val code: Int) {

    private val mockWebServer = MockWebServer()
    internal val service: DogService get() = DogService.getService(mockWebServer.url("/"))
    val codeToFile: Pair<Int, String?> get() = code to null
    val expected: T? = null
    val expectedHeaders: Headers get() = headersOf("content-length" to listOf("0"), "token" to listOf("1234"))

    @BeforeTest
    fun `start mockWebServer`() {
        mockWebServer.start()
    }

    @BeforeTest
    fun `enqueue response`() {
        mockWebServer.enqueue(mockResponse(codeToFile))
    }

    @AfterTest
    fun `close mockWebServer`() {
        mockWebServer.shutdown()
    }
}
