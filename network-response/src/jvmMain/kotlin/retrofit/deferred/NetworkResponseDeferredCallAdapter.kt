package com.javiersc.networkResponse.retrofit.deferred

import com.javiersc.networkResponse.NetworkResponse
import kotlinx.coroutines.CompletableDeferred
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Converter
import java.lang.reflect.Type

internal class NetworkResponseDeferredCallAdapter<R : Any, E : Any>(
    private val successBodyType: Type,
    private val errorConverter: Converter<ResponseBody, E>
) : CallAdapter<R, CompletableDeferred<NetworkResponse<R, E>>> {

    override fun responseType(): Type = successBodyType

    override fun adapt(call: Call<R>): CompletableDeferred<NetworkResponse<R, E>> = deferredAdapt(call, errorConverter)
}
