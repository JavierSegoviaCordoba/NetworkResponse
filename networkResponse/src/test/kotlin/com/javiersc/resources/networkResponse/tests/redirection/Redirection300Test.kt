package com.javiersc.resources.networkResponse.tests.redirection

import com.javiersc.resources.networkResponse.NetworkResponse.Redirection.MultipleChoices
import com.javiersc.resources.networkResponse.StatusCode
import com.javiersc.resources.networkResponse.config.models.Dog
import com.javiersc.resources.networkResponse.tests.BaseNullTest
import io.kotest.matchers.collections.shouldContain
import kotlinx.coroutines.runBlocking
import okhttp3.internal.toHeaderList
import org.junit.jupiter.api.Test

internal class Redirection300Test : BaseNullTest<Dog>(StatusCode.MULTIPLE_CHOICE_300.code) {

    @Test
    fun `suspend call`() = runBlocking {
        with(service.getDog()) {
            (this as MultipleChoices).headers.toHeaderList() shouldContain expectedHeader
        }
    }

    @Test
    fun `async call`() = runBlocking {
        with(service.getDogAsync().await()) {
            (this as MultipleChoices).headers.toHeaderList() shouldContain expectedHeader
        }
    }
}
