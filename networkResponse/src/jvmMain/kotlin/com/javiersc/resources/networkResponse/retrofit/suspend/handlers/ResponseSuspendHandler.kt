package com.javiersc.resources.networkResponse.retrofit.suspend.handlers

import com.javiersc.resources.networkResponse.NetworkResponse
import com.javiersc.resources.networkResponse.retrofit.suspend.NetworkResponseSuspendCall
import com.javiersc.resources.networkResponse.retrofit.utils.headers
import com.javiersc.resources.networkResponse.retrofit.utils.httpStatusCode
import com.javiersc.resources.networkResponse.utils.printlnError
import okhttp3.ResponseBody
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.Response

internal fun <R : Any, E : Any> Response<R>.responseSuspendHandler(
    errorConverter: Converter<ResponseBody, E>,
    call: NetworkResponseSuspendCall<R, E>,
    callback: Callback<NetworkResponse<R, E>>,
) {
    val errorBody: E? =
        if (errorBody()?.contentLength() == 0L) null
        else runCatching { errorBody()?.let { errorConverter.convert(it) } }
            .getOrElse { printlnError("Error body can't be serialized with the error object provided").run { null } }

    handleSuspend(call, callback, httpStatusCode, body(), errorBody, headers)
}
