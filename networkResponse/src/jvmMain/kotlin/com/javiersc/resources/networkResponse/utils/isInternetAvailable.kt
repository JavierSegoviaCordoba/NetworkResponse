package com.javiersc.resources.networkResponse.utils

import java.net.InetSocketAddress
import java.net.Socket

public actual val isInternetAvailable: Boolean
    get() = runCatching {
        Socket().apply {
            connect(InetSocketAddress(Constants.DnsIp, Constants.DnsPort), Constants.DnsTimeout)
            close()
        }
    }.isSuccess
