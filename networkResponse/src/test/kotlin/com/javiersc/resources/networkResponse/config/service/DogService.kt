package com.javiersc.resources.networkResponse.config.service

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.javiersc.resources.networkResponse.NetworkResponse
import com.javiersc.resources.networkResponse.adapter.NetworkResponseCallAdapterFactory
import com.javiersc.resources.networkResponse.config.models.Dog
import com.javiersc.resources.networkResponse.config.models.Error
import kotlinx.coroutines.Deferred
import kotlinx.serialization.json.Json
import okhttp3.HttpUrl
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET

internal interface DogService {

    @GET("dos")
    fun getDogAsync(): Deferred<NetworkResponse<Dog, Error>>

    @GET("dog")
    suspend fun getDog(): NetworkResponse<Dog, Error>

    companion object {
        fun getService(httpUrl: HttpUrl): DogService {
            val converter = Json(
                block = {
                    ignoreUnknownKeys = true
                    isLenient = true
                }
            ).asConverterFactory("application/json".toMediaType())

            val retrofit = Retrofit.Builder().apply {
                baseUrl(httpUrl)
                addCallAdapterFactory(NetworkResponseCallAdapterFactory())
                addConverterFactory(converter)
            }.build()

            return retrofit.create()
        }
    }
}
