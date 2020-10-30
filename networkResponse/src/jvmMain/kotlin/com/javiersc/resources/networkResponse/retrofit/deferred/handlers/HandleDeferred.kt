package com.javiersc.resources.networkResponse.retrofit.deferred.handlers

import com.javiersc.resources.networkResponse.NetworkResponse
import io.ktor.http.Headers
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.CompletableDeferred

internal fun <R, E> handleDeferred(
    deferred: CompletableDeferred<NetworkResponse<R, E>>,
    status: HttpStatusCode,
    body: R?,
    errorBody: E?,
    headers: Headers,
) {
    @Suppress("MagicNumber")
    when (status.value) {
        in 200..299 -> {
            if (body != null) deferred.complete(NetworkResponse.Success(body, status, headers))
            else handleNullBody(deferred, status, headers)
        }
        in 400..599 -> deferred.complete(NetworkResponse.Error(errorBody, status, headers))
    }
}

@Suppress("UNCHECKED_CAST")
private fun <E, R> handleNullBody(
    deferred: CompletableDeferred<NetworkResponse<R, E>>,
    status: HttpStatusCode,
    headers: Headers
) {
    try {
        deferred.complete(NetworkResponse.Success(Unit as R, status, headers))
    } catch (e: ClassCastException) {
        throw ClassCastException("NetworkResponse should use Unit as Success type when body is null")
    }
}
