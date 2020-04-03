package com.javiersc.resources.networkResponse.adapter.suspend.handlers

import com.javiersc.resources.networkResponse.NetworkResponse
import com.javiersc.resources.networkResponse.NetworkResponse.CustomError
import com.javiersc.resources.networkResponse.adapter.suspend.NetworkResponseSuspendCall
import com.javiersc.resources.networkResponse.adapter.utils.printlnError
import okhttp3.Headers
import okhttp3.ResponseBody
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.Response
import retrofit2.Response.success
import java.util.IllegalFormatException

internal fun <R : Any, E : Any> Response<R>.responseSuspendHandler(
    errorConverter: Converter<ResponseBody, E>,
    call: NetworkResponseSuspendCall<R, E>,
    callback: Callback<NetworkResponse<R, E>>
) {
    val body: R? = body()
    val code: Int = code()
    val headers: Headers = headers()
    val errorBody: E? = when {
        errorBody() == null -> null
        errorBody()?.contentLength() ?: 0L == 0L -> null
        else -> try {
            errorBody()?.let { errorConverter.convert(it) }
        } catch (e: IllegalFormatException) {
            printlnError("Error body can't be serialized with the error object provided")
            null
        }
    }

    @Suppress("MagicNumber")
    when (code) {
        in 200..299 -> handle2xx(call, callback, code, body, headers)
        in 100..599 -> handleAllNoSuccess(call, callback, code, errorBody, headers)
        else -> callback.onResponse(call, success(CustomError(errorBody, code, headers)))
    }
}
