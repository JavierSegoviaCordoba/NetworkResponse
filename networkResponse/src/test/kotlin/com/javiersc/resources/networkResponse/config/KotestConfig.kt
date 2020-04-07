package com.javiersc.resources.networkResponse.config

import io.kotest.core.config.AbstractProjectConfig

@Suppress("unused")
internal object KotestConfig : AbstractProjectConfig() {
    override val parallelism: Int get() = 16
}
