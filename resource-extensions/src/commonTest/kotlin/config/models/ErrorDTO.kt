package com.javiersc.networkResponse.extensions.config.models

import io.ktor.http.Headers
import io.ktor.http.HttpStatusCode
import kotlinx.serialization.Serializable

@Serializable
internal data class ErrorDTO(val message: String)

@Serializable
internal data class ErrorD(val message: String, val code: Int?, val headers: Headers?)

internal fun ErrorDTO.toErrorD(status: HttpStatusCode, headers: Headers): ErrorD =
    ErrorD(message, status.value, headers)

internal fun internetNotAvailableToErrorD(): ErrorD = ErrorD(message = "No Internet", code = null, headers = null)

internal fun remoteNotAvailableToErrorD(): ErrorD = ErrorD(message = "No remote", code = null, headers = null)

internal fun Throwable.toErrorD(): ErrorD = ErrorD(message = message!!, code = null, headers = null)
