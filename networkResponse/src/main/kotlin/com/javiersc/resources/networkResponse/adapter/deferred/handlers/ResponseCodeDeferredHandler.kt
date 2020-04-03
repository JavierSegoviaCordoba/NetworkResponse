package com.javiersc.resources.networkResponse.adapter.deferred.handlers

import com.javiersc.resources.networkResponse.NetworkResponse
import com.javiersc.resources.networkResponse.NetworkResponse.Success.NoContent
import com.javiersc.resources.networkResponse.adapter.utils.printlnError
import kotlinx.coroutines.CompletableDeferred
import okhttp3.Headers
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Response
import java.util.IllegalFormatException

internal fun <R : Any, E : Any> Response<R>.responseDeferredHandler(
    errorConverter: Converter<ResponseBody, E>,
    deferred: CompletableDeferred<NetworkResponse<R, E>>
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
    when {
        body == null && code in 200..299 -> deferred.complete(NoContent(headers))
        body != null && code in 200..299 -> handle2xx(deferred, code, body, headers)
        code in 100..599 -> handleAllNoSuccess(deferred, code, errorBody, headers)
        else -> deferred.complete(NetworkResponse.CustomError(errorBody, code, headers))
    }
}
