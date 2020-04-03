package com.javiersc.resources.networkResponse.adapter.suspend.handlers

import com.javiersc.resources.networkResponse.NetworkResponse
import com.javiersc.resources.networkResponse.NetworkResponse.CustomError
import com.javiersc.resources.networkResponse.adapter.suspend.NetworkResponseSuspendCall
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
    val errorBody = this.response()?.errorBody()?.let { errorConverter.convert(it) }
    val code = this.code()
    val headers = this.response()?.headers()

    @Suppress("MagicNumber")
    if (code in 100..599) handleAllNoSuccess(call, callback, code, errorBody, headers)
    else callback.onResponse(call, Response.success(CustomError(errorBody, code, headers)))
}
