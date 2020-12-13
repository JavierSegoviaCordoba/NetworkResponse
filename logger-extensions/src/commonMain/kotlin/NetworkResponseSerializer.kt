package com.javiersc.networkResponse.serialization.logger

import com.javiersc.logger.core.LoggerBackgroundColor.Reset
import com.javiersc.logger.core.LoggerForegroundColor.Green
import com.javiersc.logger.core.extensions.logE
import com.javiersc.logger.serialization.LoggerSerialization
import com.javiersc.logger.serialization.extensions.logSerializableD
import com.javiersc.logger.serialization.extensions.logSerializableE
import com.javiersc.networkResponse.NetworkResponse
import com.javiersc.networkResponse.NetworkResponse.Error
import com.javiersc.networkResponse.NetworkResponse.InternetNotAvailable
import com.javiersc.networkResponse.NetworkResponse.RemoteNotAvailable
import com.javiersc.networkResponse.NetworkResponse.Success
import com.javiersc.networkResponse.NetworkResponse.UnknownError
import io.ktor.util.toMap
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.serializer

public fun <S, E> NetworkResponse<S, E>.alsoPrettyPrint(
    tag: String? = null,
    successSerializer: KSerializer<S>,
    errorSerializer: KSerializer<E>,
): NetworkResponse<S, E> {
    when (val response = this) {
        is Success<S> -> logSerializableD(tag, responseSerializer(successSerializer), response.success())
        is Error<E> -> logSerializableE(tag, responseSerializer(errorSerializer), response.error())
        is RemoteNotAvailable -> if (tag != null) logE(tag, RemoteError) else logE(RemoteError)
        is InternetNotAvailable -> if (tag != null) logE(tag, InternetError) else logE(InternetError)
        is UnknownError -> with(response.throwable) {
            if (tag != null) logE(tag, stackTraceToString()) else logE(stackTraceToString())
        }
    }
    return this
}

public fun <S, E> NetworkResponse<S, E>.alsoPrettyPrint(
    tag: String? = null,
    logger: LoggerSerialization,
    successSerializer: KSerializer<S>,
    errorSerializer: KSerializer<E>,
): NetworkResponse<S, E> {
    when (val response = this) {
        is Success<S> ->
            logger.serializableC(tag, responseSerializer(successSerializer), response.success(), Reset, Green)
        is Error<E> -> logger.serializableE(tag, responseSerializer(errorSerializer), response.error())
        is RemoteNotAvailable -> if (tag != null) logger.e(tag, RemoteError) else logE(RemoteError)
        is InternetNotAvailable -> if (tag != null) logger.e(tag, InternetError) else logE(InternetError)
        is UnknownError -> with(response.throwable) {
            if (tag != null) logger.e(tag, stackTraceToString())
            else logger.e(null, stackTraceToString())
        }
    }
    return this
}

public inline fun <reified S, reified E> NetworkResponse<S, E>.alsoPrettyPrint(
    tag: String? = null,
    logger: LoggerSerialization,
): NetworkResponse<S, E> = alsoPrettyPrint(tag, logger, serializer(), serializer())

public inline fun <reified S, reified E> NetworkResponse<S, E>.alsoPrettyPrint(
    tag: String? = null,
): NetworkResponse<S, E> = alsoPrettyPrint(tag, serializer(), serializer())

internal fun <S> responseSerializer(serializer: KSerializer<S>): KSerializer<InternalResponse<S>> =
    InternalResponse.serializer(serializer)

@Serializable
internal data class InternalResponse<T>(val code: Int, val headers: Map<String, List<String>>, val body: T)

private fun <S> Success<S>.success(): InternalResponse<S> = InternalResponse(status.value, headers.toMap(), data)
private fun <E> Error<E>.error(): InternalResponse<E> = InternalResponse(status.value, headers.toMap(), error)

private const val RemoteError = "Remote not available"
private const val InternetError = "Internet not available"
