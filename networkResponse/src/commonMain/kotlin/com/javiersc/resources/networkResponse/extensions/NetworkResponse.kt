package com.javiersc.resources.networkResponse.extensions

import com.javiersc.resources.networkResponse.NetworkResponse
import com.javiersc.resources.resource.Resource
import io.ktor.http.Headers
import io.ktor.http.HttpStatusCode

/**
 * Map NetworkResponse to Resource where:
 * - NetworkResponse.Success<NR> -> Resource.Success<R>
 * - NetworkResponse.Error<NE> -> Resource.Error<E>
 * - NetworkResponse.UnknownError<Throwable> -> Resource.Error<E>
 * - NetworkResponse.InternetNotAvailable<String> -> Resource.Error<E>
 */
public fun <NR, R, NE, E> NetworkResponse<NR, NE>.toResource(
    success: (NR, HttpStatusCode, Headers) -> R,
    error: (NE?, HttpStatusCode, Headers) -> E,
    unknownError: (Throwable) -> E,
    internetNotAvailable: ((String) -> E),
): Resource<R, E> = when (this) {
    is NetworkResponse.Success -> Resource.Success(success(data, status, headers))
    is NetworkResponse.Error -> Resource.Error(error(this.error, status, headers))
    is NetworkResponse.UnknownError -> Resource.Error(unknownError(throwable))
    is NetworkResponse.InternetNotAvailable -> Resource.Error(internetNotAvailable.invoke(this.error))
}
