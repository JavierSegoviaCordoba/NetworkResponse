package com.javiersc.resources.networkResponse.retrofit.deferred.handlers

import com.javiersc.resources.networkResponse.NetworkResponse
import kotlinx.coroutines.CompletableDeferred
import okhttp3.Headers

internal fun <R, E> handleDeferred(
    deferred: CompletableDeferred<NetworkResponse<R, E>>,
    code: Int,
    body: R?,
    errorBody: E?,
    headers: Headers,
) {
    val headersMap = headers.toMultimap()
    @Suppress("MagicNumber")
    when (code) {
        in 100..199 -> deferred.complete(NetworkResponse.Info(code, headersMap))
        in 200..299 -> {
            if (body != null) deferred.complete(NetworkResponse.Success(body, code, headersMap))
            else handleNullBody(deferred, code, headersMap)
        }
        in 300..399 -> deferred.complete(NetworkResponse.Redirection(code, headersMap))
        in 400..499 -> deferred.complete(NetworkResponse.ClientError(errorBody, code, headersMap))
        in 500..599 -> deferred.complete(NetworkResponse.ServerError(errorBody, code, headersMap))
    }
}

@Suppress("UNCHECKED_CAST")
private fun <E, R> handleNullBody(
    deferred: CompletableDeferred<NetworkResponse<R, E>>,
    code: Int,
    headersMap: Map<String, List<String>>
) {
    try {
        deferred.complete(NetworkResponse.Success(Unit as R, code, headersMap))
    } catch (e: ClassCastException) {
        throw ClassCastException("NetworkResponse should use Unit as Success type when body is null")
    }
}
