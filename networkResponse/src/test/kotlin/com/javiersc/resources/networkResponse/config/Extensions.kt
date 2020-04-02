package com.javiersc.resources.networkResponse.config

import okhttp3.mockwebserver.MockResponse

internal fun Any.readResource(jsonFileName: String): String {
    return this::class.java.classLoader.getResource(jsonFileName)!!.readText()
}

internal fun mockResponse(codeToFile: Pair<Int, String?>): MockResponse {
    val (code: Int, file: String?) = codeToFile
    return MockResponse().apply { setResponseCode(code); file?.let { setBody(readResource(it)) } }
}