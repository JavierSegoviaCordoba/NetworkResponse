package com.javiersc.resources.networkResponse.config.models

import com.javiersc.resources.networkResponse.ktor.emptyHeader
import io.ktor.http.Headers
import io.ktor.http.HttpStatusCode
import kotlinx.serialization.Serializable

@Serializable
internal data class ErrorDTO(val message: String)

@Serializable
internal data class ErrorD(val message: String, val code: Int, val headers: Headers)

internal fun ErrorDTO?.toErrorD(status: HttpStatusCode, headers: Headers): ErrorD =
    ErrorD(this?.message ?: "", status.value, headers)

internal fun String.toErrorD(): ErrorD = ErrorD(message = this, code = 499, headers = emptyHeader)

internal fun Throwable.toErrorD(): ErrorD = ErrorD(message = toString(), code = 999, headers = emptyHeader)
