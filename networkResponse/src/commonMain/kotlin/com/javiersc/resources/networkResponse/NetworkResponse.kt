package com.javiersc.resources.networkResponse

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
sealed class NetworkResponse<out NR, out E> {

    @Serializable
    data class Success<out NR>(
        val data: NR,
        val code: Int,
        val headers: Headers,
    ) : NetworkResponse<NR, @Contextual Nothing>()

    @Serializable
    data class Error<out E>(
        val error: E? = null,
        val code: Int,
        val headers: Headers,
    ) : NetworkResponse<@Contextual Nothing, E>()

    @Serializable
    data class UnknownError(
        @Contextual val throwable: Throwable
    ) : NetworkResponse<@Contextual Nothing, @Contextual Nothing>()

    @Serializable
    data class InternetNotAvailable(
        val error: String
    ) : NetworkResponse<@Contextual Nothing, @Contextual Nothing>()
}

typealias Headers = Map<String, List<String>>
