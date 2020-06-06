package com.javiersc.resources.networkResponse.retrofit.config.models

import com.javiersc.resources.networkResponse.Headers
import kotlinx.serialization.Serializable

@Serializable
internal data class ErrorDTO(val message: String)

@Serializable
internal data class ErrorD(val message: String, val code: Int, val headers: Headers)

internal fun ErrorDTO?.toErrorD(code: Int, headers: Headers): ErrorD = ErrorD(this?.message ?: "", code, headers)

internal fun String.toErrorD(): ErrorD = ErrorD(message = this, code = 499, headers = emptyMap())

internal fun Throwable.toErrorD(): ErrorD = ErrorD(message = toString(), code = 999, headers = emptyMap())
