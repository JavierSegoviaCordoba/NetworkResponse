package com.javiersc.resources.networkResponse.config.extensions

import kotlinx.coroutines.runBlocking

internal actual fun Any.readResource(file: String): String = this::class.java.classLoader.getResource(file)!!.readText()

internal actual fun runBlocking(block: suspend () -> Unit) = runBlocking { block() }
