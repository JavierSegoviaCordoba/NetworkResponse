package com.javiersc.resources.networkResponse.utils

import io.ktor.http.HttpStatusCode

@PublishedApi
internal object Constants {
    const val DnsTimeout = 2000
    const val DnsIp = "8.8.8.8"
    const val DnsPort = 53
    private const val RemoteUnavailable = 598
    val HttpStatusCodeRemoteUnavailable = HttpStatusCode(RemoteUnavailable, "Remote unavailable")
}
