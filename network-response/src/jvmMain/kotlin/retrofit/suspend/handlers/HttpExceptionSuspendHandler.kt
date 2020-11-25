package com.javiersc.resources.networkResponse.retrofit.suspend.handlers

import com.javiersc.resources.networkResponse.NetworkResponse
import com.javiersc.resources.networkResponse.retrofit.suspend.NetworkResponseSuspendCall
import com.javiersc.resources.networkResponse.retrofit.utils.headers
import com.javiersc.resources.networkResponse.retrofit.utils.httpStatusCode
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

    handleSuspend(call, callback, httpStatusCode, null, errorBody, headers = headers)
}
