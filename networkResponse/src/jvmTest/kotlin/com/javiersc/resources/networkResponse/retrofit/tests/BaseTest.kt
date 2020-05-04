package com.javiersc.resources.networkResponse.retrofit.tests

import com.javiersc.resources.networkResponse.retrofit.config.mockResponse
import com.javiersc.resources.networkResponse.retrofit.config.service.DogService
import okhttp3.mockwebserver.MockWebServer
import kotlin.test.AfterTest
import kotlin.test.BeforeTest

abstract class BaseTest<T> {

    internal open val mockWebServer = MockWebServer()
    internal open val service: DogService get() = DogService.getService(mockWebServer.url("/"))
    abstract val codeToFile: Pair<Int, String?>
    abstract val expected: T
    val expectedHeader: Pair<String, List<String>> get() = "Token" to listOf("1234")

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

open class BaseNullTest<T>(private val code: Int) {

    private val mockWebServer = MockWebServer()
    internal val service: DogService get() = DogService.getService(mockWebServer.url("/"))
    val codeToFile: Pair<Int, String?> get() = code to null
    val expected: T? = null
    open val expectedHeader: Pair<String, List<String>> get() = "Token" to listOf("1234")

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
