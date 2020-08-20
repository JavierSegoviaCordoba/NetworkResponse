package com.javiersc.resources.networkResponse

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
public sealed class NetworkResponse<out NR, out E> {

    @Serializable
    public data class Success<out NR>(
        val data: NR,
        val code: Int,
        val headers: Headers,
    ) : NetworkResponse<NR, @Contextual Nothing>()

    @Serializable
    public data class Error<out E>(
        val error: E? = null,
        val code: Int,
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

public typealias Headers = Map<String, List<String>>
