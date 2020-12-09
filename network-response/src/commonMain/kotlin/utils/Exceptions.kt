package com.javiersc.networkResponse.utils

import kotlinx.serialization.SerializationException

internal val SerializationException.hasBody: Boolean
    get() = !this.message?.substringAfter("JSON input:").isNullOrBlank()
