package com.javiersc.resources.networkResponse

import io.ktor.http.Headers
import io.ktor.http.HttpStatusCode
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

/**
 * Sealed class which cover usual use cases related which network responses
 */
@Serializable
public sealed class NetworkResponse<out NR, out E> {

    @Serializable
    public data class Success<out NR>(
        val data: NR,
        @Contextual
        val status: HttpStatusCode,
        val headers: Headers,
    ) : NetworkResponse<NR, @Contextual Nothing>()

    @Serializable
    public data class Error<out E>(
        val error: E?,
        @Contextual
        val status: HttpStatusCode,
        val headers: Headers,
    ) : NetworkResponse<@Contextual Nothing, E>()

    @Serializable
    public data class UnknownError(
        @Contextual val throwable: Throwable
    ) : NetworkResponse<@Contextual Nothing, @Contextual Nothing>()

    @Serializable
    public data class InternetNotAvailable(
        val error: String
    ) : NetworkResponse<@Contextual Nothing, @Contextual Nothing>()
}
