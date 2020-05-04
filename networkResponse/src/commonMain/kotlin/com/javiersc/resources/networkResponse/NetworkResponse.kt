package com.javiersc.resources.networkResponse

import kotlinx.serialization.Serializable
import kotlinx.serialization.ContextualSerialization as CS

@Serializable
sealed class NetworkResponse<out NR, out E> {

    @Serializable
    data class Info(
        val code: Int,
        val headers: Headers,
    ) : NetworkResponse<@CS Nothing, @CS Nothing>()

    @Serializable
    data class Success<out NR>(
        val data: NR,
        val code: Int,
        val headers: Headers,
    ) : NetworkResponse<NR, @CS Nothing>() {
        data class Empty(
            val code: Int,
            val headers: Headers,
        ) : NetworkResponse<@CS Nothing, @CS Nothing>()
    }

    @Serializable
    data class Redirection(
        val code: Int,
        val headers: Headers,
    ) : NetworkResponse<@CS Nothing, @CS Nothing>()

    @Serializable
    data class ClientError<out E>(
        val error: E? = null,
        val code: Int,
        val headers: Headers,
    ) : NetworkResponse<@CS Nothing, E>()

    @Serializable
    data class ServerError<out E>(
        val error: E? = null,
        val code: Int,
        val headers: Headers,
    ) : NetworkResponse<@CS Nothing, E>()

    @Serializable
    data class InternetNotAvailable(val error: String) : NetworkResponse<@CS Nothing, @CS Nothing>()
}

typealias Headers = Map<String, List<String>>
