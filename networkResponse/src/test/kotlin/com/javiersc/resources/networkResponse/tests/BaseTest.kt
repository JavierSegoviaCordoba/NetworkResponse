package com.javiersc.resources.networkResponse.tests

import com.javiersc.resources.networkResponse.config.mockResponse
import com.javiersc.resources.networkResponse.config.service.DogService
import okhttp3.internal.http2.Header
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.jupiter.api.BeforeEach

internal interface BaseTest<T> {

    val mockWebServer: MockWebServer get() = mockWebServerSingleton
    val service: DogService get() = DogService.getService(mockWebServer.url("/"))
    val codeToFile: Pair<Int, String?>
    val expected: T
    val expectedHeader: Header get() = Header("Token", "1234")

    @Before
    fun `start mockWebServer`() {
        mockWebServer.start()
    }

    @BeforeEach
    fun `enqueue response`() {
        mockWebServer.enqueue(mockResponse(codeToFile))
    }

    @After
    fun `close mockWebServer`() {
        mockWebServer.shutdown()
    }

    companion object {
        private val mockWebServerSingleton = MockWebServer()
    }
}

internal abstract class BaseNullTest<T>(private val code: Int) : BaseTest<T?> {

    override val codeToFile: Pair<Int, String?> get() = code to null
    override val expected: T? = null

    @BeforeEach
    override fun `enqueue response`() {
        mockWebServer.enqueue(mockResponse(codeToFile))
    }
}
