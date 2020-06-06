package com.javiersc.resources.networkResponse.retrofit.config.models

import com.javiersc.resources.networkResponse.Headers
import kotlinx.serialization.Serializable

@Serializable
internal data class DogDTO(val id: Int, val name: String, val age: Int)

@Serializable
internal data class Dog(val name: String, val code: Int, val headers: Headers)

internal fun DogDTO.toDog(code: Int, headers: Headers) = Dog(name, code, headers)

internal fun toNullDog(name: Unit, code: Int, headers: Headers) = Dog(name.toString(), code, headers)

internal fun toDefaultDog(code: Int, headers: Headers) = Dog("Auri", code, headers)
