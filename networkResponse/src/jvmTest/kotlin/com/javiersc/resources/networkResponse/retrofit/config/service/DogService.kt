package com.javiersc.resources.networkResponse.retrofit.config.service

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.javiersc.resources.networkResponse.NetworkResponse
import com.javiersc.resources.networkResponse.retrofit.NetworkResponseCallAdapterFactory
import com.javiersc.resources.networkResponse.retrofit.config.models.DogDTO
import com.javiersc.resources.networkResponse.retrofit.config.models.ErrorDTO
import kotlinx.coroutines.Deferred
import kotlinx.serialization.json.Json
import okhttp3.HttpUrl
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET
import java.util.concurrent.TimeUnit

internal interface DogService {

    @GET("dos")
    fun getDogAsync(): Deferred<NetworkResponse<DogDTO, ErrorDTO>>

    @GET("dog")
    suspend fun getDog(): NetworkResponse<DogDTO, ErrorDTO>

    @GET("dog")
    fun getDogWithoutBodyAsync(): Deferred<NetworkResponse<Unit, ErrorDTO>>

    @GET("dog")
    suspend fun getDogWithoutBody(): NetworkResponse<Unit, ErrorDTO>

    companion object {
        fun getService(httpUrl: HttpUrl, timeoutMillis: Long = 200): DogService {
            val converter = Json(
                block = {
                    ignoreUnknownKeys = true
                    isLenient = true
                }
            ).asConverterFactory("application/json".toMediaType())

            val okHttpClient = OkHttpClient.Builder().apply {
                callTimeout(timeoutMillis, TimeUnit.MILLISECONDS)
                connectTimeout(timeoutMillis, TimeUnit.MILLISECONDS)
            }.build()

            val retrofit = Retrofit.Builder().apply {
                baseUrl(httpUrl)
                client(okHttpClient)
                addCallAdapterFactory(NetworkResponseCallAdapterFactory())
                addConverterFactory(converter)
            }.build()

            return retrofit.create()
        }
    }
}
