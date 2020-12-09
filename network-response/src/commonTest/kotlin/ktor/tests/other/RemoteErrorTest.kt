package com.javiersc.networkResponse.ktor.tests.other

import com.javiersc.networkResponse.NetworkResponse
import com.javiersc.networkResponse.config.models.DogDTO
import com.javiersc.networkResponse.config.models.ErrorDTO
import com.javiersc.networkResponse.ktor.NetworkResponse
import com.javiersc.networkResponse.ktor.tests.BaseTest
import com.javiersc.networkResponse.runTestBlocking
import io.kotest.matchers.types.shouldBeTypeOf
import io.ktor.client.request.get
import kotlin.test.Test

internal class RemoteErrorTest : BaseTest<DogDTO, ErrorDTO>() {

    override val codeToFile = 200 to "malformedjson"
    override val expected = NetworkResponse.RemoteNotAvailable

    @Test
    fun `Request 200 with a malformed json`() = runTestBlocking {
        NetworkResponse<DogDTO, ErrorDTO> { client.get("remote-unavailable") }
            .shouldBeTypeOf<NetworkResponse.RemoteNotAvailable>()
    }
}
