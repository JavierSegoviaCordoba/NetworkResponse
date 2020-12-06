package com.javiersc.resources.networkResponse

import io.ktor.http.Headers
import io.ktor.http.HttpStatusCode

/**
 * Sealed class which cover usual use cases related which network responses
 */
public sealed class NetworkResponse<out NR, out E> {

    public data class Success<out NR>(
        val data: NR,
        val status: HttpStatusCode,
        val headers: Headers,
    ) : NetworkResponse<NR, Nothing>()

    public data class Error<out E>(
        val error: E?,
        val status: HttpStatusCode,
        val headers: Headers,
    ) : NetworkResponse<Nothing, E>()

    public data class UnknownError(val throwable: Throwable) : NetworkResponse<Nothing, Nothing>()

    public object RemoteNotAvailable : NetworkResponse<Nothing, Nothing>()

    public object InternetNotAvailable : NetworkResponse<Nothing, Nothing>()
}
