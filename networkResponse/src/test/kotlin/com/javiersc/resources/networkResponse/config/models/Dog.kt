package com.javiersc.resources.networkResponse.config.models

import kotlinx.serialization.Serializable

@Serializable
internal data class Dog(val id: Int, val name: String, val age: Int)

internal val Dog.unused: String get() = this.run { return "No content" }