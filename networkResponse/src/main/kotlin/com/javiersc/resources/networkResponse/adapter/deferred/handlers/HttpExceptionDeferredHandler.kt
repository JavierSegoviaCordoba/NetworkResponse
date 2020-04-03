package com.javiersc.resources.networkResponse.adapter.deferred.handlers

import com.javiersc.resources.networkResponse.NetworkResponse
import kotlinx.coroutines.CompletableDeferred
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.HttpException

internal fun <R : Any, E : Any> HttpException.httpExceptionDeferredHandler(
    errorConverter: Converter<ResponseBody, E>,
    deferred: CompletableDeferred<NetworkResponse<R, E>>
) {
    val errorBody = this.response()?.errorBody()?.let { errorConverter.convert(it) }
    val code = this.code()
    val headers = this.response()?.headers()

    @Suppress("MagicNumber")
    if (code in 100..599) handleAllNoSuccess(deferred, code, errorBody, headers)
    else deferred.complete(NetworkResponse.CustomError(errorBody, code, headers))
}
