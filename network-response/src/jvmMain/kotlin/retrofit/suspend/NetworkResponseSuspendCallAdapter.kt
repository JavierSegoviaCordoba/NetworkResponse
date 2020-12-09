package com.javiersc.networkResponse.retrofit.suspend

import com.javiersc.networkResponse.NetworkResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Converter
import java.lang.reflect.Type

internal class NetworkResponseSuspendCallAdapter<R : Any, E : Any>(
    private val successType: Type,
    private val errorBodyConverter: Converter<ResponseBody, E>
) : CallAdapter<R, Call<NetworkResponse<R, E>>> {

    override fun responseType(): Type = successType

    override fun adapt(call: Call<R>): Call<NetworkResponse<R, E>> {
        return NetworkResponseSuspendCall(call, errorBodyConverter)
    }
}
