package com.javiersc.resources.networkResponse.retrofit.utils

import com.javiersc.resources.networkResponse.retrofit.utils.Constants.DNS_IP
import com.javiersc.resources.networkResponse.retrofit.utils.Constants.DNS_PORT
import com.javiersc.resources.networkResponse.retrofit.utils.Constants.DNS_TIMEOUT
import java.net.InetSocketAddress
import java.net.Socket

internal val isInternetAvailable: Boolean
    get() = runCatching {
        Socket().apply {
            connect(InetSocketAddress(DNS_IP, DNS_PORT), DNS_TIMEOUT)
            close()
        }
    }.isSuccess
