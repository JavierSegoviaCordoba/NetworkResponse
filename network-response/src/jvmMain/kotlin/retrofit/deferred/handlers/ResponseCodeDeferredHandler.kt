package com.javiersc.networkResponse.retrofit.deferred.handlers

import com.javiersc.networkResponse.NetworkResponse
import com.javiersc.networkResponse.retrofit.utils.headers
import com.javiersc.networkResponse.retrofit.utils.httpStatusCode
import com.javiersc.networkResponse.utils.printlnError
import kotlinx.coroutines.CompletableDeferred
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Response

internal fun <R : Any, E : Any> Response<R>.responseDeferredHandler(
    errorConverter: Converter<ResponseBody, E>,
    deferred: CompletableDeferred<NetworkResponse<R, E>>,
) {
    val errorBody: E? =
        if (errorBody()?.contentLength() == 0L) null
        else runCatching { errorBody()?.let { errorConverter.convert(it) } }
            .getOrElse { printlnError("Error body can't be serialized with the error object provided").run { null } }

    handleDeferred(deferred, httpStatusCode, body(), errorBody, headers)
}
