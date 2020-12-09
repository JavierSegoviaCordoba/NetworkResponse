package com.javiersc.networkResponse

import io.ktor.http.Headers
import io.ktor.http.HttpStatusCode

/**
 * Sealed class which cover usual use cases related which network responses
 */
public sealed class NetworkResponse<out NR, out E> {

    /**
     * Success branch (code 200 to 299)
     * Use `Unit` as `S` type if the body is empty
     */
    public data class Success<out S>(
        val data: S,
        val status: HttpStatusCode,
        val headers: Headers,
    ) : NetworkResponse<S, Nothing>()

    /**
     * Error branch (400 to 499)
     * Use `Unit` as `E` type if the body is empty
     */
    public data class Error<out E>(
        val error: E,
        val status: HttpStatusCode,
        val headers: Headers,
    ) : NetworkResponse<Nothing, E>()

    /**
     * Any unknown error like Cast exceptions, etc.
     */
    public data class UnknownError(val throwable: Throwable) : NetworkResponse<Nothing, Nothing>()

    /**
     * Remote is down
     */
    public object RemoteNotAvailable : NetworkResponse<Nothing, Nothing>()

    /**
     * Client has no Internet connection
     */
    public object InternetNotAvailable : NetworkResponse<Nothing, Nothing>()
}
