package com.javiersc.resources.networkResponse.adapter.deferred

import com.javiersc.resources.networkResponse.NetworkResponse
import com.javiersc.resources.networkResponse.adapter.deferred.handlers.httpExceptionDeferredHandler
import com.javiersc.resources.networkResponse.adapter.deferred.handlers.responseDeferredHandler
import com.javiersc.resources.networkResponse.adapter.utils.hasBody
import com.javiersc.resources.networkResponse.adapter.utils.isInternetAvailable
import com.javiersc.resources.networkResponse.adapter.utils.printlnError
import com.javiersc.resources.networkResponse.adapter.utils.printlnWarning
import kotlinx.coroutines.CompletableDeferred
import kotlinx.serialization.json.JsonDecodingException
import kotlinx.serialization.json.JsonException
import okhttp3.Headers
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.HttpException
import retrofit2.Response
import java.io.EOFException
import java.io.InterruptedIOException
import java.net.ConnectException
import java.net.UnknownHostException

internal fun <R : Any, E : Any> deferredAdapt(
    call: Call<R>,
    errorConverter: Converter<ResponseBody, E>
): CompletableDeferred<NetworkResponse<R, E>> {
    val deferred = CompletableDeferred<NetworkResponse<R, E>>()

    deferred.invokeOnCompletion { if (deferred.isCancelled) call.cancel() }

    call.enqueue(object : Callback<R> {
        override fun onFailure(call: Call<R>, throwable: Throwable) {
            when (throwable) {
                is UnknownHostException, is ConnectException, is InterruptedIOException ->
                    onCommonConnectionException(deferred, throwable)
                is EOFException -> onEOFException(deferred)
                is IllegalStateException -> onIllegalStateException(throwable)
                is HttpException -> onHttpException(deferred, errorConverter, throwable)
                is JsonDecodingException ->
                    if (throwable.hasBody) onIllegalStateException(throwable)
                    else onEOFException(deferred)
                else -> throw UnknownError("${throwable.message}")
            }
        }

        override fun onResponse(call: Call<R>, response: Response<R>) {
            response.responseDeferredHandler(errorConverter, deferred)
        }
    })
    return deferred
}

private fun <R, E> onEOFException(deferred: CompletableDeferred<NetworkResponse<R, E>>) {
    printlnWarning(
        """
           | # # # # # # # # # # # # # # WARNING # # # # # # # # # # # # # # # # # # #
           | # Every 2XX response should have a body except 204/205, as the response #
           | # was empty, the NetworkResponse is transformed to NoContent (204) and  #
           | # the headers are lost                                                  #
           | # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
        """.trimMargin()
    )
    deferred.complete(
        NetworkResponse.Success
            .NoContent(headers = Headers.of(mutableMapOf("Content-Length" to "0")))
    )
}

private fun onIllegalStateException(throwable: Throwable) {
    printlnError(
        """
           | # # # # # # # # # # # # # # ERROR # # # # # # # # # # # # # # #
           | # Response body can't be serialized with the object provided" #
           | # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
        """.trimMargin()
    )
    throw JsonException(throwable.localizedMessage)
}

private fun <R, E> onCommonConnectionException(
    deferred: CompletableDeferred<NetworkResponse<R, E>>,
    throwable: Throwable
) {
    val message = "${throwable.message}"
    if (isInternetAvailable) deferred.complete(NetworkResponse.RemoteError(message))
    else deferred.complete(NetworkResponse.InternetNotAvailable(message))
}

private fun <R : Any, E : Any> onHttpException(
    deferred: CompletableDeferred<NetworkResponse<R, E>>,
    errorConverter: Converter<ResponseBody, E>,
    exception: HttpException
) = exception.httpExceptionDeferredHandler(errorConverter, deferred)
