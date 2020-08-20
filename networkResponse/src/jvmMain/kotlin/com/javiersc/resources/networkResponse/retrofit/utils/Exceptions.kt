package com.javiersc.resources.networkResponse.retrofit.utils

import kotlinx.serialization.SerializationException

internal val SerializationException.hasBody: Boolean
    get() = !this.message?.substringAfter("JSON input:").isNullOrBlank()
