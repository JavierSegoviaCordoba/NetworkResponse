package com.javiersc.resources.networkResponse.retrofit.suspend.handlers

import com.javiersc.resources.networkResponse.NetworkResponse
import com.javiersc.resources.networkResponse.NetworkResponse.Error
import com.javiersc.resources.networkResponse.NetworkResponse.Success
import com.javiersc.resources.networkResponse.retrofit.suspend.NetworkResponseSuspendCall
import io.ktor.http.Headers
import io.ktor.http.HttpStatusCode
import retrofit2.Callback
import retrofit2.Response

@Suppress("LongParameterList")
internal fun <R : Any, E : Any> handleSuspend(
    call: NetworkResponseSuspendCall<R, E>,
    callback: Callback<NetworkResponse<R, E>>,
    status: HttpStatusCode,
    body: R?,
    errorBody: E?,
    headers: Headers,
) = with(callback) {
    @Suppress("MagicNumber")
    when (status.value) {
        in 200..299 -> {
            if (body != null) onResponse(call, Response.success(Success(body, status, headers)))
            else handleNullBody(callback, call, status, headers)
        }
        in 400..599 -> onResponse(call, Response.success(Error(errorBody, status, headers)))
    }
}

@Suppress("UNCHECKED_CAST")
private fun <E : Any, R : Any> handleNullBody(
    callback: Callback<NetworkResponse<R, E>>,
    call: NetworkResponseSuspendCall<R, E>,
    status: HttpStatusCode,
    headers: Headers
) {
    try {
        callback.onResponse(call, Response.success(Success(Unit as R, status, headers)))
    } catch (e: ClassCastException) {
        throw ClassCastException("NetworkResponse should use Unit as Success type when body is null")
    }
}
