package com.javiersc.resources.networkResponse

import kotlinx.serialization.ContextualSerialization
import kotlinx.serialization.Serializable

@Serializable
sealed class NetworkResponse<out NR, out E> {

    @Serializable
    data class Success<out NR>(
        val data: NR,
        val code: Int,
        val headers: Headers,
    ) : NetworkResponse<NR, @ContextualSerialization Nothing>()

    @Serializable
    data class Error<out E>(
        val error: E? = null,
        val code: Int,
        val headers: Headers,
    ) : NetworkResponse<@ContextualSerialization Nothing, E>()

    @Serializable
    data class UnknownError(
        @ContextualSerialization val throwable: Throwable
    ) : NetworkResponse<@ContextualSerialization Nothing, @ContextualSerialization Nothing>()

    @Serializable
    data class InternetNotAvailable(
        val error: String
    ) : NetworkResponse<@ContextualSerialization Nothing, @ContextualSerialization Nothing>()
}

typealias Headers = Map<String, List<String>>
