package com.javiersc.networkResponse.extensions

import com.javiersc.networkResponse.NetworkResponse
import com.javiersc.networkResponse.extensions.config.models.Dog
import com.javiersc.networkResponse.extensions.config.models.DogDTO
import com.javiersc.networkResponse.extensions.config.models.ErrorD
import com.javiersc.networkResponse.extensions.config.models.ErrorDTO
import com.javiersc.networkResponse.extensions.config.models.internetNotAvailableToErrorD
import com.javiersc.networkResponse.extensions.config.models.remoteNotAvailableToErrorD
import com.javiersc.networkResponse.extensions.config.models.toDog
import com.javiersc.networkResponse.extensions.config.models.toErrorD
import com.javiersc.resources.resource.Resource
import io.kotest.matchers.shouldBe
import io.ktor.http.HttpStatusCode.Companion.NotFound
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.http.headersOf
import kotlin.test.Test

internal class ResourceExtensionsTest {

    private val headers = headersOf("Content-Type" to listOf("application/json"))

    @Test
    fun `Map Success to Resource`() {
        val dogDTO = DogDTO(1, "Auri", 7)
        val expectedDog = Dog("Auri", 200, headers)

        val networkResponse: NetworkResponse<DogDTO, ErrorDTO> = NetworkResponse.Success(dogDTO, OK, headers)
        networkResponse.toResource(
            success = DogDTO::toDog,
            error = ErrorDTO::toErrorD,
            unknownError = Throwable::toErrorD,
            remoteNotAvailable = ::remoteNotAvailableToErrorD,
            internetNotAvailable = ::internetNotAvailableToErrorD,
        ) shouldBe Resource.Success(expectedDog)
    }

    @Test
    fun `Map Error to Resource`() {
        val errorDTO = ErrorDTO("error")
        val expectedError = ErrorD("error", 404, headers)

        val networkResponse: NetworkResponse<DogDTO, ErrorDTO> = NetworkResponse.Error(errorDTO, NotFound, headers)
        networkResponse.toResource(
            success = DogDTO::toDog,
            error = ErrorDTO::toErrorD,
            unknownError = Throwable::toErrorD,
            remoteNotAvailable = ::remoteNotAvailableToErrorD,
            internetNotAvailable = ::internetNotAvailableToErrorD,
        ) shouldBe Resource.Error(expectedError)
    }

    @Test
    fun `Map UnknownError to Resource`() {
        val exception = IllegalStateException("error")
        val expectedError = ErrorD("error", null, null)

        val networkResponse: NetworkResponse<DogDTO, ErrorDTO> = NetworkResponse.UnknownError(exception)
        networkResponse.toResource(
            success = DogDTO::toDog,
            error = ErrorDTO::toErrorD,
            unknownError = Throwable::toErrorD,
            remoteNotAvailable = ::remoteNotAvailableToErrorD,
            internetNotAvailable = ::internetNotAvailableToErrorD,
        ) shouldBe Resource.Error(expectedError)
    }

    @Test
    fun `Map RemoteNotAvailable to Resource`() {
        val expectedError = ErrorD("No remote", null, null)

        val networkResponse: NetworkResponse<DogDTO, ErrorDTO> = NetworkResponse.RemoteNotAvailable
        networkResponse.toResource(
            success = DogDTO::toDog,
            error = ErrorDTO::toErrorD,
            unknownError = Throwable::toErrorD,
            remoteNotAvailable = ::remoteNotAvailableToErrorD,
            internetNotAvailable = ::internetNotAvailableToErrorD,
        ) shouldBe Resource.Error(expectedError)
    }

    @Test
    fun `Map InternetNotAvailable to Resource`() {
        val expectedError = ErrorD("No Internet", null, null)

        val networkResponse: NetworkResponse<DogDTO, ErrorDTO> = NetworkResponse.InternetNotAvailable
        networkResponse.toResource(
            success = DogDTO::toDog,
            error = ErrorDTO::toErrorD,
            unknownError = Throwable::toErrorD,
            remoteNotAvailable = ::remoteNotAvailableToErrorD,
            internetNotAvailable = ::internetNotAvailableToErrorD,
        ) shouldBe Resource.Error(expectedError)
    }
}
