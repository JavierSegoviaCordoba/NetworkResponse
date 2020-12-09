package com.javiersc.networkResponse.extensions

import com.javiersc.networkResponse.NetworkResponse
import com.javiersc.resources.resource.Resource
import io.ktor.http.Headers
import io.ktor.http.HttpStatusCode
import kotlin.jvm.JvmName

/**
 * Map NetworkResponse to Resource where:
 * - NetworkResponse.Success<NR> -> Resource.Success<R>
 * - NetworkResponse.Error<NE> -> Resource.Error<E>
 * - NetworkResponse.UnknownError<Throwable> -> Resource.Error<E>
 * - NetworkResponse.RemoteNotAvailable -> Resource.Error<E>
 * - NetworkResponse.InternetNotAvailable -> Resource.Error<E>
 */
public fun <NR, R, NE, E> NetworkResponse<NR, NE>.toResource(
    success: (NR, HttpStatusCode, Headers) -> R,
    error: (NE, HttpStatusCode, Headers) -> E,
    unknownError: (Throwable) -> E,
    remoteNotAvailable: () -> E,
    internetNotAvailable: () -> E,
): Resource<R, E> = when (this) {
    is NetworkResponse.Success -> Resource.Success(success(data, status, headers))
    is NetworkResponse.Error -> Resource.Error(error(this.error, status, headers))
    is NetworkResponse.UnknownError -> Resource.Error(unknownError(throwable))
    is NetworkResponse.RemoteNotAvailable -> Resource.Error(remoteNotAvailable())
    is NetworkResponse.InternetNotAvailable -> Resource.Error(internetNotAvailable())
}

/**
 * Simpler function for mapping NetworkResponse to Resource where:
 * - NetworkResponse.Success<NR> -> Resource.Success<R>
 * - NetworkResponse.Error<NE> -> Resource.Error<E>
 * - NetworkResponse.UnknownError<Throwable> -> Resource.Error<E>
 * - NetworkResponse.RemoteNotAvailable -> Resource.Error<E>
 * - NetworkResponse.InternetNotAvailable -> Resource.Error<E>
 */
@JvmName("toResource2")
public fun <NR, R, NE, E> NetworkResponse<NR, NE>.toResource(
    success: (NR) -> R,
    error: (NE) -> E,
    unknownError: (Throwable) -> E,
    remoteNotAvailable: () -> E,
    internetNotAvailable: () -> E,
): Resource<R, E> = when (this) {
    is NetworkResponse.Success -> Resource.Success(success(data))
    is NetworkResponse.Error -> Resource.Error(error(this.error))
    is NetworkResponse.UnknownError -> Resource.Error(unknownError(throwable))
    is NetworkResponse.RemoteNotAvailable -> Resource.Error(remoteNotAvailable())
    is NetworkResponse.InternetNotAvailable -> Resource.Error(internetNotAvailable())
}

/**
 * Simpler function for mapping NetworkResponse to Resource where:
 * - NetworkResponse.Success<NR> -> Resource.Success<R>
 * - NetworkResponse.Error<NE> -> Resource.Error<E>
 * - NetworkResponse.UnknownError<Throwable> -> Resource.Error<E>
 * - NetworkResponse.RemoteNotAvailable> -> Resource.Error<E>
 * - NetworkResponse.InternetNotAvailable -> Resource.Error<E>
 */
@JvmName("toResource3")
public fun <NR, R, NE, E> NetworkResponse<NR, NE>.toResource(
    success: (NR) -> R,
    error: (HttpStatusCode) -> E,
    unknownError: (Throwable) -> E,
    remoteNotAvailable: () -> E,
    internetNotAvailable: () -> E,
): Resource<R, E> = when (this) {
    is NetworkResponse.Success -> Resource.Success(success(data))
    is NetworkResponse.Error -> Resource.Error(error(status))
    is NetworkResponse.UnknownError -> Resource.Error(unknownError(throwable))
    is NetworkResponse.RemoteNotAvailable -> Resource.Error(remoteNotAvailable())
    is NetworkResponse.InternetNotAvailable -> Resource.Error(internetNotAvailable())
}

/**
 * Simpler function for mapping NetworkResponse to Resource where:
 * - NetworkResponse.Success<NR> -> Resource.Success<R>
 * - NetworkResponse.Error -> Resource.Error<E>
 *
 * Notes:
 * - There is no error data
 * - UnknownError, RemoteNotAvailable and InternetNotAvailable are mapped with error too.
 */
@JvmName("toResource4")
public fun <NR, R, NE, E> NetworkResponse<NR, NE>.toResource(
    success: (NR) -> R,
    error: () -> E,
): Resource<R, E> = when (this) {
    is NetworkResponse.Success -> Resource.Success(success(data))
    is NetworkResponse.Error -> Resource.Error(error())
    is NetworkResponse.UnknownError -> Resource.Error(error())
    is NetworkResponse.RemoteNotAvailable -> Resource.Error(error())
    is NetworkResponse.InternetNotAvailable -> Resource.Error(error())
}
