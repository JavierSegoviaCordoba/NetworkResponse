package com.javiersc.resources.networkResponse.tests.redirection

import com.javiersc.resources.networkResponse.NetworkResponse.Redirection.PermanentRedirect
import com.javiersc.resources.networkResponse.StatusCode
import com.javiersc.resources.networkResponse.config.models.Dog
import com.javiersc.resources.networkResponse.tests.BaseNullTest
import io.kotest.matchers.collections.shouldContain
import kotlinx.coroutines.runBlocking
import okhttp3.internal.toHeaderList
import org.junit.jupiter.api.Test

internal class Redirection308Test : BaseNullTest<Dog>(StatusCode.PERMANENT_REDIRECT_308.code) {

    @Test
    fun `suspend call`() = runBlocking {
        with(service.getDog()) {
            (this as PermanentRedirect).headers.toHeaderList() shouldContain expectedHeader
        }
    }

    @Test
    fun `async call`() = runBlocking {
        with(service.getDogAsync().await()) {
            (this as PermanentRedirect).headers.toHeaderList() shouldContain expectedHeader
        }
    }
}
