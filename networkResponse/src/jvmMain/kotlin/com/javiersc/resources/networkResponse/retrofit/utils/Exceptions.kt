package com.javiersc.resources.networkResponse.retrofit.utils

import kotlinx.serialization.json.JsonDecodingException

internal val JsonDecodingException.hasBody: Boolean
    get() = !this.message?.substringAfter("JSON input:").isNullOrBlank()
