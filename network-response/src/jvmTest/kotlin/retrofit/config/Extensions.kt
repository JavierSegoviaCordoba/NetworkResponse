package com.javiersc.resources.networkResponse.retrofit.config

import com.javiersc.resources.networkResponse.readResource
import okhttp3.mockwebserver.MockResponse

internal fun mockResponse(codeToFile: Pair<Int, String?>): MockResponse {
    val (code: Int, file: String?) = codeToFile
    return MockResponse().apply {
        setResponseCode(code)
        file?.let { setBody(readResource(it)) }
        setHeader(name = "Token", value = 1234)
    }
}
