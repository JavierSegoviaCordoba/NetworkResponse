package com.javiersc.resources.networkResponse.adapter.utils

import kotlinx.serialization.json.JsonDecodingException

internal val JsonDecodingException.hasBody: Boolean
    get() = !this.message?.substringAfter("JSON input:").isNullOrBlank()
