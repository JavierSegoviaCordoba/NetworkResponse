package com.javiersc.resources.networkResponse.config.extensions

internal expect fun Any.readResource(file: String): String

internal expect fun runBlocking(block: suspend () -> Unit)
