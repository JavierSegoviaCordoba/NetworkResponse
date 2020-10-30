@file:Suppress("TooGenericExceptionCaught")

package com.javiersc.resources.networkResponse.ktor

import com.javiersc.resources.networkResponse.NetworkResponse
import com.javiersc.resources.networkResponse.utils.Constants
import com.javiersc.resources.networkResponse.utils.isInternetAvailable
import io.ktor.client.features.ResponseException
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.readText
import io.ktor.util.network.UnresolvedAddressException
import io.ktor.utils.io.errors.IOException
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

/**
 * Transform a request made by a Ktor client to NetworkResponse
 */
public suspend inline operator fun <reified NR, reified E> NetworkResponse.Companion.invoke(
    request: () -> HttpResponse
): NetworkResponse<NR, E> = try {
    request().asNetworkResponse()
} catch (throwable: Throwable) {
    throwable.asNetworkResponse()
}

/**
 * Transform a request made by a Ktor client to NetworkResponse
 */
@Suppress("FunctionNaming", "FunctionName")
public suspend inline fun <reified NR, reified E> NetworkResponse(
    request: () -> HttpResponse
): NetworkResponse<NR, E> = try {
    request().asNetworkResponse()
} catch (throwable: Throwable) {
    throwable.asNetworkResponse()
}

@PublishedApi
internal suspend inline fun <reified NR, reified E> HttpResponse.asNetworkResponse(): NetworkResponse<NR, E> =
    with(this) { NetworkResponse.Success(decode(), status, headers) }

@PublishedApi
internal suspend inline fun <reified NR, reified E> ResponseException.serializeAsError(): NetworkResponse<NR, E> =
    try {
        with(response) { NetworkResponse.Error<E>(decode(), status, headers) }
    } catch (throwable: Throwable) {
        NetworkResponse.UnknownError(throwable)
    }

@PublishedApi
internal suspend inline fun <reified T> HttpResponse.decode(): T = Json.decodeFromString(readText())

@PublishedApi
internal suspend inline fun <reified NR, reified E> Throwable.asNetworkResponse(): NetworkResponse<NR, E> =
    when (this) {
        is ResponseException -> serializeAsError()
        is UnresolvedAddressException -> remoteOrInternetNotAvailable()
        is IOException -> remoteOrInternetNotAvailable()
        else -> NetworkResponse.UnknownError(this)
    }

@PublishedApi
internal fun <NR, E> Exception.remoteOrInternetNotAvailable(): NetworkResponse<NR, E> =
    if (isInternetAvailable) NetworkResponse.Error(null, Constants.HttpStatusCodeRemoteUnavailable, emptyHeader)
    else NetworkResponse.InternetNotAvailable(message ?: "Internet not available")
