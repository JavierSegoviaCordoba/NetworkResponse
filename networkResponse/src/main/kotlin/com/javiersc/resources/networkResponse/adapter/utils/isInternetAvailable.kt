package com.javiersc.resources.networkResponse.adapter.utils

import com.javiersc.resources.networkResponse.adapter.utils.Constants.GOOGLE_DNS_IP
import com.javiersc.resources.networkResponse.adapter.utils.Constants.GOOGLE_DNS_PORT
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket

internal val isInternetAvailable: Boolean
    get() {
        return try {
            val timeoutMs = Constants.DNS_TIMEOUT
            val socket = Socket()
            val socketAddress = InetSocketAddress(GOOGLE_DNS_IP, GOOGLE_DNS_PORT)
            socket.connect(socketAddress, timeoutMs)
            socket.close()
            true
        } catch (e: IOException) {
            false
        }
    }
