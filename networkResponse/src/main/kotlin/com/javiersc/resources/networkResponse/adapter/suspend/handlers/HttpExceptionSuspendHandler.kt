package com.javiersc.resources.networkResponse.adapter.suspend.handlers

import com.javiersc.resources.networkResponse.NetworkResponse
import com.javiersc.resources.networkResponse.NetworkResponse.CustomError
import com.javiersc.resources.networkResponse.adapter.suspend.NetworkResponseSuspendCall
import okhttp3.Headers
import okhttp3.ResponseBody
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.HttpException
import retrofit2.Response

internal fun <R : Any, E : Any> HttpException.httpExceptionSuspendHandler(
    errorConverter: Converter<ResponseBody, E>,
    call: NetworkResponseSuspendCall<R, E>,
    callback: Callback<NetworkResponse<R, E>>
) {
    val errorBody: E? = this.response()?.errorBody()?.let(errorConverter::convert)
    val code: Int = this.code()
    val headers: Headers = this.response()
        ?.headers()
        ?: Headers.of(mutableMapOf("Content-Length" to "0"))

    @Suppress("MagicNumber")
    if (code in 100..599) handleAllNoSuccess(call, callback, code, errorBody, headers)
    else callback.onResponse(call, Response.success(CustomError(errorBody, code, headers)))
}
