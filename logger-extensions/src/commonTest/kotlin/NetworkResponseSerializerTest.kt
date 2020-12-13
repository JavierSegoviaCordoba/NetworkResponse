import com.javiersc.networkResponse.NetworkResponse
import com.javiersc.networkResponse.serialization.logger.alsoPrettyPrint
import io.kotest.matchers.shouldBe
import io.ktor.http.HttpStatusCode.Companion.NotFound
import io.ktor.http.HttpStatusCode.Companion.OK
import io.ktor.http.headersOf
import kotlinx.serialization.Serializable
import kotlin.test.Test

internal class NetworkResponseSerializerTest {
    private val headers = headersOf("ContentType" to listOf("application/json"))

    @Test
    fun `Pretty print Success`() {
        val networkResponse: NetworkResponse<DogDTO, ErrorDTO> = NetworkResponse.Success(DogDTO("Auri"), OK, headers)
        val tag = "Success test"

        networkResponse.alsoPrettyPrint(tag, DogDTO.serializer(), ErrorDTO.serializer()) shouldBe networkResponse
        networkResponse.alsoPrettyPrint(tag) shouldBe networkResponse
    }

    @Test
    fun `Pretty print Error`() {
        val networkResponse: NetworkResponse<DogDTO, ErrorDTO> =
            NetworkResponse.Error(ErrorDTO(":("), NotFound, headers)
        val tag = "Error test"

        networkResponse.alsoPrettyPrint(tag, DogDTO.serializer(), ErrorDTO.serializer()) shouldBe networkResponse
        networkResponse.alsoPrettyPrint(tag) shouldBe networkResponse
    }

    @Test
    fun `Pretty print RemoteNotAvailable`() {
        val networkResponse: NetworkResponse<DogDTO, ErrorDTO> = NetworkResponse.RemoteNotAvailable
        val tag = "Remote not available test"

        networkResponse.alsoPrettyPrint(tag, DogDTO.serializer(), ErrorDTO.serializer()) shouldBe networkResponse
        networkResponse.alsoPrettyPrint(tag) shouldBe networkResponse
    }

    @Test
    fun `Pretty print InternetNotAvailable`() {
        val networkResponse: NetworkResponse<DogDTO, ErrorDTO> = NetworkResponse.InternetNotAvailable
        val tag = "Internet not available test"

        networkResponse.alsoPrettyPrint(tag, DogDTO.serializer(), ErrorDTO.serializer()) shouldBe networkResponse
        networkResponse.alsoPrettyPrint(tag) shouldBe networkResponse
    }

    @Test
    fun `Pretty print UnknownError`() {
        val exception = IllegalStateException("Unknown error")
        val networkResponse: NetworkResponse<DogDTO, ErrorDTO> = NetworkResponse.UnknownError(exception)
        val tag = "Unknown error test"

        networkResponse.alsoPrettyPrint(tag, DogDTO.serializer(), ErrorDTO.serializer()) shouldBe networkResponse
        networkResponse.alsoPrettyPrint(tag) shouldBe networkResponse
    }
}

@Serializable
private data class DogDTO(val name: String)

@Serializable
private data class ErrorDTO(val error: String)
