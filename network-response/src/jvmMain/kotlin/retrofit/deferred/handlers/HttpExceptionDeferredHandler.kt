package com.javiersc.networkResponse.retrofit.deferred.handlers

import com.javiersc.networkResponse.NetworkResponse
import com.javiersc.networkResponse.retrofit.utils.headers
import com.javiersc.networkResponse.retrofit.utils.httpStatusCode
import kotlinx.coroutines.CompletableDeferred
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.HttpException

internal fun <R : Any, E : Any> HttpException.httpExceptionDeferredHandler(
    errorConverter: Converter<ResponseBody, E>,
    deferred: CompletableDeferred<NetworkResponse<R, E>>
) {
    val errorBody: E? = response()?.errorBody()?.let(errorConverter::convert)
    handleDeferred(deferred = deferred, status = httpStatusCode, body = null, errorBody = errorBody, headers)
}
