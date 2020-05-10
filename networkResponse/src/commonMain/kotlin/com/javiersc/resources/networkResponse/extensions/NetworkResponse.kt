package com.javiersc.resources.networkResponse.extensions

import com.javiersc.resources.networkResponse.Headers
import com.javiersc.resources.networkResponse.NetworkResponse
import com.javiersc.resources.resource.Resource

inline fun <reified NR, reified R, reified NE, reified E> NetworkResponse<NR, NE>.toResource(
    crossinline success: (NR, Int, Headers) -> R,
    noinline successEmpty: ((Int, Headers) -> R)? = null,
    crossinline error: (NE?, Int, Headers) -> E,
    noinline internetNotAvailable: ((String) -> E),
): Resource<R, E> = when (this) {
    is NetworkResponse.Info -> TODO()
    is NetworkResponse.Success -> Resource.Success(success(data, code, headers))
    is NetworkResponse.Success.Empty -> {
        if (successEmpty != null) Resource.Success(successEmpty.invoke(code, headers))
        else throw NotImplementedError("The response was empty and successEmpty is not implemented")
    }
    is NetworkResponse.Redirection -> TODO()
    is NetworkResponse.ClientError -> Resource.Error(error(this.error, code, headers))
    is NetworkResponse.ServerError -> Resource.Error(error(this.error, code, headers))
    is NetworkResponse.InternetNotAvailable -> Resource.Error(internetNotAvailable.invoke(this.error))
}