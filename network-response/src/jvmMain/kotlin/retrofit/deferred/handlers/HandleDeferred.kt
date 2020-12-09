package com.javiersc.networkResponse.retrofit.deferred.handlers

import com.javiersc.networkResponse.NetworkResponse
import com.javiersc.networkResponse.utils.printlnError
import io.ktor.http.Headers
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.CompletableDeferred

@Suppress("UNCHECKED_CAST")
internal fun <R, E> handleDeferred(
    deferred: CompletableDeferred<NetworkResponse<R, E>>,
    status: HttpStatusCode,
    body: R?,
    errorBody: E?,
    headers: Headers,
) {
    @Suppress("MagicNumber")
    when (status.value) {
        in 200..299 -> {
            if (body != null) deferred.complete(NetworkResponse.Success(body, status, headers))
            else handleNullBody(deferred, status, headers)
        }
        in 400..599 -> {
            if (errorBody != null) deferred.complete(NetworkResponse.Error(errorBody, status, headers))
            else handleNullErrorBody(deferred, status, headers)
        }
    }
}

@Suppress("UNCHECKED_CAST")
private fun <E, R> handleNullBody(
    deferred: CompletableDeferred<NetworkResponse<R, E>>,
    status: HttpStatusCode,
    headers: Headers,
) {
    try {
        deferred.complete(NetworkResponse.Success(Unit as R, status, headers))
    } catch (e: ClassCastException) {
        printlnError(
            """
               | # # # # # # # # # # # # # # ERROR # # # # # # # # # # # # # # # # # #
               | # NetworkResponse should use Unit as Success type when body is null #
               | # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
            """.trimMargin()
        )
        deferred.complete(NetworkResponse.UnknownError(e))
    }
}

@Suppress("UNCHECKED_CAST")
private fun <E, R> handleNullErrorBody(
    deferred: CompletableDeferred<NetworkResponse<R, E>>,
    status: HttpStatusCode,
    headers: Headers,
) {
    try {
        deferred.complete(NetworkResponse.Error(Unit as E, status, headers))
    } catch (e: ClassCastException) {
        printlnError(
            """
               | # # # # # # # # # # # # # # ERROR # # # # # # # # # # # # # # # # #
               | # NetworkResponse should use Unit as Error type when body is null #
               | # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # # #
            """.trimMargin()
        )
        deferred.complete(NetworkResponse.UnknownError(e))
    }
}
