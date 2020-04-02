package com.javiersc.resources.networkResponse.tests

import com.javiersc.resources.networkResponse.config.mockResponse
import com.javiersc.resources.networkResponse.config.service.DogService
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.jupiter.api.BeforeEach

internal interface BaseTest<T> {

    private val mockWebServer: MockWebServer get() = mockWebServerSingleton
    val service: DogService get() = DogService.getService(mockWebServer.url("/"))
    val codeToFile: Pair<Int, String?>
    val expected: T

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

internal abstract class BaseNullTest<T>(private val code: Int) {

    private val mockWebServer: MockWebServer get() = mockWebServerSingleton
    val service: DogService get() = DogService.getService(mockWebServer.url("/"))

    @Before
    fun `start mockWebServer`() {
        mockWebServer.start()
    }

    @BeforeEach
    fun `enqueue response`() {
        mockWebServer.enqueue(mockResponse(code to null))
    }

    @After
    fun `close mockWebServer`() {
        mockWebServer.shutdown()
    }

    companion object {
        private val mockWebServerSingleton = MockWebServer()
    }
}