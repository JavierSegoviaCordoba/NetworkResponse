package com.javiersc.resources.networkResponse.retrofit.suspend.handlers

import com.javiersc.resources.networkResponse.NetworkResponse
import com.javiersc.resources.networkResponse.NetworkResponse.ClientError
import com.javiersc.resources.networkResponse.NetworkResponse.Info
import com.javiersc.resources.networkResponse.NetworkResponse.Redirection
import com.javiersc.resources.networkResponse.NetworkResponse.ServerError
import com.javiersc.resources.networkResponse.NetworkResponse.Success
import com.javiersc.resources.networkResponse.retrofit.suspend.NetworkResponseSuspendCall
import okhttp3.Headers
import retrofit2.Callback
import retrofit2.Response

@Suppress("LongParameterList")
internal fun <R : Any, E : Any> handleSuspend(
    call: NetworkResponseSuspendCall<R, E>,
    callback: Callback<NetworkResponse<R, E>>,
    code: Int,
    body: R?,
    errorBody: E?,
    headers: Headers,
) = with(callback) {
    val headersMap = headers.toMultimap()
    @Suppress("MagicNumber")
    when (code) {
        in 100..199 -> onResponse(call, Response.success(Info(code, headersMap)))
        in 200..299 -> {
            if (body != null) onResponse(call, Response.success(Success(body, code, headersMap)))
            else handleNullBody(callback, call, code, headersMap)
        }
        in 300..399 -> onResponse(call, Response.success(Redirection(code, headersMap)))
        in 400..499 -> onResponse(call, Response.success(ClientError(errorBody, code, headersMap)))
        in 500..599 -> onResponse(call, Response.success(ServerError(errorBody, code, headersMap)))
    }
}

@Suppress("UNCHECKED_CAST")
private fun <E : Any, R : Any> handleNullBody(
    callback: Callback<NetworkResponse<R, E>>,
    call: NetworkResponseSuspendCall<R, E>,
    code: Int,
    headersMap: Map<String, List<String>>
) {
    try {
        callback.onResponse(call, Response.success(Success(Unit as R, code, headersMap)))
    } catch (e: ClassCastException) {
        throw ClassCastException("NetworkResponse should use Unit as Success type when body is null")
    }
}
