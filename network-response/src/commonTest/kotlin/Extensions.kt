package com.javiersc.networkResponse

internal expect fun Any.readResource(file: String): String

internal expect fun runTestBlocking(block: suspend () -> Unit)
