package com.javiersc.resources.networkResponse.retrofit.deferred

import com.javiersc.resources.networkResponse.NetworkResponse
import com.javiersc.resources.networkResponse.isInternetAvailable
import com.javiersc.resources.networkResponse.ktor.emptyHeader
import com.javiersc.resources.networkResponse.retrofit.deferred.handlers.httpExceptionDeferredHandler
import com.javiersc.resources.networkResponse.retrofit.deferred.handlers.responseDeferredHandler
import com.javiersc.resources.networkResponse.utils.hasBody
import com.javiersc.resources.networkResponse.utils.printlnError
import com.javiersc.resources.networkResponse.utils.printlnWarning
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.CompletableDeferred
import kotlinx.serialization.SerializationException
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
    errorConverter: Converter<ResponseBody, E>,
): CompletableDeferred<NetworkResponse<R, E>> {
    val deferred = CompletableDeferred<NetworkResponse<R, E>>()

    deferred.invokeOnCompletion { if (deferred.isCancelled) call.cancel() }

    call.enqueue(
        object : Callback<R> {
            override fun onResponse(call: Call<R>, response: Response<R>) {
                response.responseDeferredHandler(errorConverter, deferred)
            }

            override fun onFailure(call: Call<R>, throwable: Throwable) {
                when (throwable) {
                    is UnknownHostException, is ConnectException, is InterruptedIOException ->
                        onCommonConnectionException(deferred)
                    is EOFException -> onEOFException(deferred)
                    is IllegalStateException -> onIllegalStateException(deferred, throwable)
                    is HttpException -> onHttpException(deferred, errorConverter, throwable)
                    is SerializationException ->
                        if (throwable.hasBody) onIllegalStateException(deferred, throwable)
                        else onEOFException(deferred)
                    else -> deferred.complete(NetworkResponse.UnknownError(throwable))
                }
            }
        }
    )
    return deferred
}

private fun <R, E> onEOFException(deferred: CompletableDeferred<NetworkResponse<R, E>>) {
    printlnWarning(
        """
           | # # # # # # # # # # # # # # WARNING # # # # # # # # # # # # # # # # # # #
           | # Every 2XX response should have a body except 204/205, as the response #
           | # was empty, the response is transformed to Success with code 204 and   #
           | # the headers are lost. The type should be Unit.                        #
           | # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
        """.trimMargin()
    )

    @Suppress("UNCHECKED_CAST")
    try {
        deferred.complete(NetworkResponse.Success(Unit as R, HttpStatusCode.NoContent, emptyHeader))
    } catch (e: ClassCastException) {
        throw ClassCastException("NetworkResponse should use Unit as Success type when there isn't body")
    }
}

private fun <R, E> onIllegalStateException(deferred: CompletableDeferred<NetworkResponse<R, E>>, throwable: Throwable) {
    printlnError(
        """
           | # # # # # # # # # # # # # # ERROR # # # # # # # # # # # # # # #
           | # Response body can't be serialized with the object provided  #
           | # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
        """.trimMargin()
    )
    deferred.complete(NetworkResponse.UnknownError(throwable))
}

private fun <R, E> onCommonConnectionException(deferred: CompletableDeferred<NetworkResponse<R, E>>) {
    deferred
        .complete(if (isInternetAvailable) NetworkResponse.RemoteNotAvailable else NetworkResponse.InternetNotAvailable)
}

private fun <R : Any, E : Any> onHttpException(
    deferred: CompletableDeferred<NetworkResponse<R, E>>,
    errorConverter: Converter<ResponseBody, E>,
    exception: HttpException,
) = exception.httpExceptionDeferredHandler(errorConverter, deferred)
