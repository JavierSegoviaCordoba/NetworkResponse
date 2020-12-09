package com.javiersc.networkResponse.extensions.config.models

import io.ktor.http.Headers
import io.ktor.http.HttpStatusCode
import kotlinx.serialization.Serializable

@Serializable
internal data class DogDTO(val id: Int, val name: String, val age: Int)

@Serializable
internal data class Dog(val name: String, val code: Int, val headers: Headers)

internal fun DogDTO.toDog(status: HttpStatusCode, headers: Headers) = Dog(name, status.value, headers)
