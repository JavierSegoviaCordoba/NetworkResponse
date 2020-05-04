package com.javiersc.resources.networkResponse.retrofit.config

import io.kotest.core.config.AbstractProjectConfig

@Suppress("unused")
internal object KotestConfig : AbstractProjectConfig() {
    override val parallelism: Int get() = Integer.MAX_VALUE
}
