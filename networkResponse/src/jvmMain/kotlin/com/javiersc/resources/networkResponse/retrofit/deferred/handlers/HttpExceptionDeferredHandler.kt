package com.javiersc.resources.networkResponse.retrofit.deferred.handlers

import com.javiersc.resources.networkResponse.NetworkResponse
import kotlinx.coroutines.CompletableDeferred
import okhttp3.Headers
import okhttp3.Headers.Companion.toHeaders
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.HttpException

internal fun <R : Any, E : Any> HttpException.httpExceptionDeferredHandler(
    errorConverter: Converter<ResponseBody, E>,
    deferred: CompletableDeferred<NetworkResponse<R, E>>
) {
    val errorBody: E? = response()?.errorBody()?.let(errorConverter::convert)
    val headers: Headers = this.response()?.headers()
        ?: mutableMapOf("Content-Length" to "0").toHeaders()

    handleDeferred(
        deferred,
        code(),
        null,
        errorBody,
        headers)
}
