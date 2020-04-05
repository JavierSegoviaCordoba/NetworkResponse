package com.javiersc.resources.networkResponse

import kotlinx.serialization.Serializable
import okhttp3.Headers
import kotlinx.serialization.ContextualSerialization as CS

@Serializable
sealed class NetworkResponse<out NR, out E> {

    @Serializable
    sealed class Info : NetworkResponse<@CS Nothing, @CS Nothing>() {

        @Serializable
        data class Continue(val headers: @CS Headers) : Info()

        @Serializable
        data class SwitchingProtocol(val headers: @CS Headers) : Info()

        @Serializable
        data class Processing(val headers: @CS Headers) : Info()

        @Serializable
        data class EarlyHints(val headers: @CS Headers) : Info()

        @Serializable
        data class Custom(val code: Int, val headers: @CS Headers) : Info()
    }

    @Serializable
    sealed class Success<out NR> : NetworkResponse<NR, @CS Nothing>() {

        @Serializable
        data class OK<out NR>(val value: NR, val headers: @CS Headers) : Success<NR>()

        @Serializable
        data class Created<out NR>(val value: NR, val headers: @CS Headers) : Success<NR>()

        @Serializable
        data class Accepted<out NR>(val value: NR, val headers: @CS Headers) : Success<NR>()

        @Serializable
        data class NonAuthoritativeInformation<out NR>(
            val value: NR,
            val headers: @CS Headers
        ) : Success<NR>()

        @Serializable
        data class NoContent<out NR>(val headers: @CS Headers) : Success<NR>()

        @Serializable
        data class ResetContent<out NR>(val headers: @CS Headers) : Success<NR>()

        @Serializable
        data class PartialContent<out NR>(
            val value: NR,
            val headers: @CS Headers
        ) : Success<NR>()

        @Serializable
        data class MultiStatus<out NR>(
            val value: NR,
            val headers: @CS Headers
        ) : Success<NR>()

        @Serializable
        data class AlreadyReported<out NR>(
            val value: NR,
            val headers: @CS Headers
        ) : Success<NR>()

        @Serializable
        data class ImUsed<out NR>(val value: NR, val headers: @CS Headers) : Success<NR>()

        @Serializable
        data class Custom<out NR>(
            val value: NR,
            val code: Int,
            val headers: @CS Headers
        ) : Success<NR>()
    }

    @Serializable
    sealed class Redirection : NetworkResponse<@CS Nothing, @CS Nothing>() {

        @Serializable
        data class MultipleChoices(val headers: @CS Headers) : Redirection()

        @Serializable
        data class MovedPermanently(val headers: @CS Headers) : Redirection()

        @Serializable
        data class Found(val headers: @CS Headers) : Redirection()

        @Serializable
        data class SeeOther(val headers: @CS Headers) : Redirection()

        @Serializable
        data class NotModified(val headers: @CS Headers) : Redirection()

        @Serializable
        data class UseProxy(val headers: @CS Headers) : Redirection()

        @Serializable
        data class SwitchProxy(val headers: @CS Headers) : Redirection()

        @Serializable
        data class TemporaryRedirect(val headers: @CS Headers) : Redirection()

        @Serializable
        data class PermanentRedirect(val headers: @CS Headers) : Redirection()

        @Serializable
        data class Custom(val code: Int, val headers: @CS Headers) : Redirection()
    }

    @Serializable
    sealed class ClientError<out E> : NetworkResponse<@CS Nothing, E>() {

        @Serializable
        data class BadRequest<out E>(
            val error: E? = null,
            val headers: @CS Headers
        ) : ClientError<E>()

        @Serializable
        data class Unauthorized<out E>(
            val error: E? = null,
            val headers: @CS Headers
        ) : ClientError<E>()

        @Serializable
        data class PaymentRequired<out E>(
            val error: E? = null,
            val headers: @CS Headers
        ) : ClientError<E>()

        @Serializable
        data class Forbidden<out E>(
            val error: E? = null,
            val headers: @CS Headers
        ) : ClientError<E>()

        @Serializable
        data class NotFound<out E>(
            val error: E? = null,
            val headers: @CS Headers
        ) : ClientError<E>()

        @Serializable
        data class MethodNotAllowed<out E>(
            val error: E? = null,
            val headers: @CS Headers
        ) : ClientError<E>()

        @Serializable
        data class NotAcceptable<out E>(
            val error: E? = null,
            val headers: @CS Headers
        ) : ClientError<E>()

        @Serializable
        data class ProxyAuthenticationRequired<out E>(
            val error: E? = null,
            val headers: @CS Headers
        ) : ClientError<E>()

        @Serializable
        data class RequestTimeout<out E>(
            val error: E? = null,
            val headers: @CS Headers
        ) : ClientError<E>()

        @Serializable
        data class Conflict<out E>(
            val error: E? = null,
            val headers: @CS Headers
        ) : ClientError<E>()

        @Serializable
        data class Gone<out E>(
            val error: E? = null,
            val headers: @CS Headers
        ) : ClientError<E>()

        @Serializable
        data class LengthRequired<out E>(
            val error: E? = null,
            val headers: @CS Headers
        ) : ClientError<E>()

        @Serializable
        data class PreconditionFailed<out E>(
            val error: E? = null,
            val headers: @CS Headers
        ) : ClientError<E>()

        @Serializable
        data class PayloadTooLarge<out E>(
            val error: E? = null,
            val headers: @CS Headers
        ) : ClientError<E>()

        @Serializable
        data class UriTooLong<out E>(
            val error: E? = null,
            val headers: @CS Headers
        ) : ClientError<E>()

        @Serializable
        data class UnsupportedMediaType<out E>(
            val error: E? = null,
            val headers: @CS Headers
        ) : ClientError<E>()

        @Serializable
        data class RequestedRangeNotSatisfiable<out E>(
            val error: E? = null,
            val headers: @CS Headers
        ) : ClientError<E>()

        @Serializable
        data class ExpectationFailed<out E>(
            val error: E? = null,
            val headers: @CS Headers
        ) : ClientError<E>()

        @Serializable
        data class ImATeapot<out E>(
            val error: E? = null,
            val headers: @CS Headers
        ) : ClientError<E>()

        @Serializable
        data class MisdirectedRequest<out E>(
            val error: E? = null,
            val headers: @CS Headers
        ) : ClientError<E>()

        @Serializable
        data class UnprocessableEntity<out E>(
            val error: E? = null,
            val headers: @CS Headers
        ) : ClientError<E>()

        @Serializable
        data class Locked<out E>(
            val error: E? = null,
            val headers: @CS Headers
        ) : ClientError<E>()

        @Serializable
        data class FailedDependency<out E>(
            val error: E? = null,
            val headers: @CS Headers
        ) : ClientError<E>()

        @Serializable
        data class UpgradeRequired<out E>(
            val error: E? = null,
            val headers: @CS Headers
        ) : ClientError<E>()

        @Serializable
        data class PreconditionRequired<out E>(
            val error: E? = null,
            val headers: @CS Headers
        ) : ClientError<E>()

        @Serializable
        data class TooManyRequest<out E>(
            val error: E? = null,
            val headers: @CS Headers
        ) : ClientError<E>()

        @Serializable
        data class RequestHeaderFieldsTooLarge<out E>(
            val error: E? = null,
            val headers: @CS Headers
        ) : ClientError<E>()

        @Serializable
        data class UnavailableForLegalReasons<out E>(
            val error: E? = null,
            val headers: @CS Headers
        ) : ClientError<E>()

        @Serializable
        data class Custom<out E>(
            val error: E? = null,
            val code: Int,
            val headers: @CS Headers
        ) : ClientError<E>()
    }

    @Serializable
    sealed class ServerError<out E> : NetworkResponse<@CS Nothing, E>() {

        @Serializable
        data class InternalServerError<out E>(
            val error: E? = null,
            val headers: @CS Headers
        ) : ServerError<E>()

        @Serializable
        data class NotImplemented<out E>(
            val error: E? = null,
            val headers: @CS Headers
        ) : ServerError<E>()

        @Serializable
        data class BadGateway<out E>(
            val error: E? = null,
            val headers: @CS Headers
        ) : ServerError<E>()

        @Serializable
        data class ServiceUnavailable<out E>(
            val error: E? = null,
            val headers: @CS Headers
        ) : ServerError<E>()

        @Serializable
        data class GatewayTimeout<out E>(
            val error: E? = null,
            val headers: @CS Headers
        ) : ServerError<E>()

        @Serializable
        data class HttpVersionNotSupported<out E>(
            val error: E? = null,
            val headers: @CS Headers
        ) : ServerError<E>()

        @Serializable
        data class VariantAlsoNegotiates<out E>(
            val error: E? = null,
            val headers: @CS Headers
        ) : ServerError<E>()

        @Serializable
        data class InsufficientStorage<out E>(
            val error: E? = null,
            val headers: @CS Headers
        ) : ServerError<E>()

        @Serializable
        data class LoopDetected<out E>(
            val error: E? = null,
            val headers: @CS Headers
        ) : ServerError<E>()

        @Serializable
        data class NotExtended<out E>(
            val error: E? = null,
            val headers: @CS Headers
        ) : ServerError<E>()

        @Serializable
        data class NetworkAuthenticationRequired<out E>(
            val error: E? = null,
            val headers: @CS Headers
        ) : ServerError<E>()

        @Serializable
        data class Custom<out E>(
            val error: E? = null,
            val code: Int,
            val headers: @CS Headers
        ) : ServerError<E>()
    }

    @Serializable
    data class CustomError<out E>(
        val error: E? = null,
        val code: Int,
        val headers: @CS Headers
    ) : NetworkResponse<@CS Nothing, E>()

    @Serializable
    data class InternetNotAvailable(
        val error: String? = null
    ) : NetworkResponse<@CS Nothing, @CS Nothing>()

    @Serializable
    data class RemoteError(val error: String? = null) : NetworkResponse<@CS Nothing, @CS Nothing>()
}
