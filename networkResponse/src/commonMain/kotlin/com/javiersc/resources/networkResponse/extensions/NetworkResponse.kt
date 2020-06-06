package com.javiersc.resources.networkResponse.extensions

import com.javiersc.resources.networkResponse.Headers
import com.javiersc.resources.networkResponse.NetworkResponse
import com.javiersc.resources.resource.Resource

inline fun <reified NR, reified R, reified NE, reified E> NetworkResponse<NR, NE>.toResource(
    crossinline success: (NR, Int, Headers) -> R,
    crossinline error: (NE?, Int, Headers) -> E,
    crossinline unknownError: (Throwable) -> E,
    noinline internetNotAvailable: ((String) -> E),
): Resource<R, E> = when (this) {
    is NetworkResponse.Success -> Resource.Success(success(data, code, headers))
    is NetworkResponse.Error -> Resource.Error(error(this.error, code, headers))
    is NetworkResponse.UnknownError -> Resource.Error(unknownError(throwable))
    is NetworkResponse.InternetNotAvailable -> Resource.Error(internetNotAvailable.invoke(this.error))
}
