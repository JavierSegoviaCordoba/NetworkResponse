package com.javiersc.resources.networkResponse.adapter.utils

import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket

internal val isInternetAvailable: Boolean
    get() {
        return try {
            val timeoutMs = Constants.DEFAULT_TIMEOUT
            val socket = Socket()
            val socketAddress = InetSocketAddress("8.8.8.8", Constants.GOOGLE_DNS_PORT)
            socket.connect(socketAddress, timeoutMs)
            socket.close()

            true
        } catch (e: IOException) {
            false
        }
    }
