package com.javiersc.resources.networkResponse.retrofit.suspend.handlers

import com.javiersc.resources.networkResponse.NetworkResponse
import com.javiersc.resources.networkResponse.retrofit.suspend.NetworkResponseSuspendCall
import okhttp3.Headers
import okhttp3.Headers.Companion.toHeaders
import okhttp3.ResponseBody
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.HttpException

internal fun <R : Any, E : Any> HttpException.httpExceptionSuspendHandler(
    errorConverter: Converter<ResponseBody, E>,
    call: NetworkResponseSuspendCall<R, E>,
    callback: Callback<NetworkResponse<R, E>>,
) {
    val errorBody: E? = response()?.errorBody()?.let(errorConverter::convert)
    val headers: Headers = response()?.headers()
        ?: mutableMapOf("Content-Length" to "0").toHeaders()

    handleSuspend(call, callback, code(), null, errorBody, headers)
}
