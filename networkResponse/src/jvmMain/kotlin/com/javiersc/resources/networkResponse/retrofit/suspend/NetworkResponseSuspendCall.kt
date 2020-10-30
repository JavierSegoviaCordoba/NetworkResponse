package com.javiersc.resources.networkResponse.retrofit.suspend

import com.javiersc.resources.networkResponse.NetworkResponse
import com.javiersc.resources.networkResponse.NetworkResponse.InternetNotAvailable
import com.javiersc.resources.networkResponse.ktor.emptyHeader
import com.javiersc.resources.networkResponse.retrofit.suspend.handlers.httpExceptionSuspendHandler
import com.javiersc.resources.networkResponse.retrofit.suspend.handlers.responseSuspendHandler
import com.javiersc.resources.networkResponse.utils.Constants.HttpStatusCodeRemoteUnavailable
import com.javiersc.resources.networkResponse.utils.hasBody
import com.javiersc.resources.networkResponse.utils.isInternetAvailable
import com.javiersc.resources.networkResponse.utils.printlnError
import com.javiersc.resources.networkResponse.utils.printlnWarning
import kotlinx.serialization.SerializationException
import okhttp3.Request
import okhttp3.ResponseBody
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.HttpException
import retrofit2.Response
import java.io.EOFException
import java.io.InterruptedIOException
import java.net.ConnectException
import java.net.UnknownHostException

internal class NetworkResponseSuspendCall<R : Any, E : Any>(
    private val backingCall: Call<R>,
    private val errorConverter: Converter<ResponseBody, E>,
) : Call<NetworkResponse<R, E>> {

    override fun enqueue(callback: Callback<NetworkResponse<R, E>>) = synchronized(this) {
        backingCall.enqueue(
            object : Callback<R> {
                override fun onResponse(call: Call<R>, response: Response<R>) {
                    response.responseSuspendHandler(
                        errorConverter,
                        this@NetworkResponseSuspendCall,
                        callback
                    )
                }

                override fun onFailure(call: Call<R>, throwable: Throwable) {
                    when (throwable) {
                        is UnknownHostException, is ConnectException, is InterruptedIOException ->
                            onCommonConnectionExceptions(callback, throwable)
                        is EOFException -> onEOFException(callback)
                        is IllegalStateException -> onIllegalStateException(callback, throwable)
                        is HttpException -> onHttpException(callback, errorConverter, throwable)
                        is SerializationException ->
                            if (throwable.hasBody) onIllegalStateException(callback, throwable)
                            else onEOFException(callback)
                        else -> Response.success(NetworkResponse.UnknownError(throwable))
                    }
                }
            }
        )
    }

    override fun isExecuted(): Boolean = synchronized(this) { backingCall.isExecuted }

    override fun clone(): Call<NetworkResponse<R, E>> =
        NetworkResponseSuspendCall(backingCall.clone(), errorConverter)

    override fun isCanceled(): Boolean = synchronized(this) { backingCall.isCanceled }

    override fun cancel() = synchronized(this) { backingCall.cancel() }

    override fun execute(): Response<NetworkResponse<R, E>> =
        throw UnsupportedOperationException("Suspend call does not support synchronous execution")

    override fun request(): Request = backingCall.request()

    override fun timeout(): Timeout = backingCall.timeout()
}

private fun <R, E> Call<NetworkResponse<R, E>>.onEOFException(callback: Callback<NetworkResponse<R, E>>) {
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
        callback.onResponse(
            this,
            Response.success(NetworkResponse.Success(Unit as R, HttpStatusCodeRemoteUnavailable, emptyHeader))
        )
    } catch (e: ClassCastException) {
        throw ClassCastException("NetworkResponse should use Unit as Success type when there isn't body")
    }
}

private fun <R, E> Call<NetworkResponse<R, E>>.onIllegalStateException(
    callback: Callback<NetworkResponse<R, E>>,
    throwable: Throwable
) {
    printlnError(
        """
           | # # # # # # # # # # # # # # ERROR # # # # # # # # # # # # # # #
           | # Response body can't be serialized with the object provided  #
           | # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
        """.trimMargin()
    )
    callback.onResponse(this, Response.success(NetworkResponse.UnknownError(throwable)))
}

private fun <R : Any, E : Any> NetworkResponseSuspendCall<R, E>.onCommonConnectionExceptions(
    callback: Callback<NetworkResponse<R, E>>,
    throwable: Throwable,
) {
    val message = "${throwable.message}"

    callback.onResponse(
        this,
        if (isInternetAvailable) {
            Response.success(NetworkResponse.Error(null, HttpStatusCodeRemoteUnavailable, emptyHeader))
        } else Response.success(InternetNotAvailable(message))
    )
}

private fun <R : Any, E : Any> NetworkResponseSuspendCall<R, E>.onHttpException(
    callback: Callback<NetworkResponse<R, E>>,
    errorConverter: Converter<ResponseBody, E>,
    exception: HttpException,
) = exception.httpExceptionSuspendHandler(errorConverter, this, callback)
