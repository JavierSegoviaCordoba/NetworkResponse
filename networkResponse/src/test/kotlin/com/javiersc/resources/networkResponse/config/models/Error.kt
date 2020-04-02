package com.javiersc.resources.networkResponse.config.models

import kotlinx.serialization.Serializable

@Serializable
internal data class Error(val message: String)

internal val Error?.text: String get() = this!!.message
internal val Error?.unused: String get() = "$this is Unused"