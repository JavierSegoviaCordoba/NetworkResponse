package com.javiersc.networkResponse.extensions.config.models

import io.ktor.http.Headers
import io.ktor.http.headersOf

internal val emptyHeader: Headers get() = headersOf("Content-Length", listOf("0"))
