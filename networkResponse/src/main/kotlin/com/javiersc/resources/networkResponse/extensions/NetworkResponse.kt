package com.javiersc.resources.networkResponse.extensions

import com.javiersc.resources.networkResponse.NetworkResponse
import com.javiersc.resources.networkResponse.NetworkResponse.ClientError
import com.javiersc.resources.networkResponse.NetworkResponse.ClientError.BadRequest
import com.javiersc.resources.networkResponse.NetworkResponse.ClientError.Conflict
import com.javiersc.resources.networkResponse.NetworkResponse.ClientError.ExpectationFailed
import com.javiersc.resources.networkResponse.NetworkResponse.ClientError.FailedDependency
import com.javiersc.resources.networkResponse.NetworkResponse.ClientError.Forbidden
import com.javiersc.resources.networkResponse.NetworkResponse.ClientError.Gone
import com.javiersc.resources.networkResponse.NetworkResponse.ClientError.ImATeapot
import com.javiersc.resources.networkResponse.NetworkResponse.ClientError.LengthRequired
import com.javiersc.resources.networkResponse.NetworkResponse.ClientError.Locked
import com.javiersc.resources.networkResponse.NetworkResponse.ClientError.MethodNotAllowed
import com.javiersc.resources.networkResponse.NetworkResponse.ClientError.MisdirectedRequest
import com.javiersc.resources.networkResponse.NetworkResponse.ClientError.NotAcceptable
import com.javiersc.resources.networkResponse.NetworkResponse.ClientError.NotFound
import com.javiersc.resources.networkResponse.NetworkResponse.ClientError.PayloadTooLarge
import com.javiersc.resources.networkResponse.NetworkResponse.ClientError.PaymentRequired
import com.javiersc.resources.networkResponse.NetworkResponse.ClientError.PreconditionFailed
import com.javiersc.resources.networkResponse.NetworkResponse.ClientError.PreconditionRequired
import com.javiersc.resources.networkResponse.NetworkResponse.ClientError.ProxyAuthenticationRequired
import com.javiersc.resources.networkResponse.NetworkResponse.ClientError.RequestHeaderFieldsTooLarge
import com.javiersc.resources.networkResponse.NetworkResponse.ClientError.RequestTimeout
import com.javiersc.resources.networkResponse.NetworkResponse.ClientError.RequestedRangeNotSatisfiable
import com.javiersc.resources.networkResponse.NetworkResponse.ClientError.TooManyRequest
import com.javiersc.resources.networkResponse.NetworkResponse.ClientError.Unauthorized
import com.javiersc.resources.networkResponse.NetworkResponse.ClientError.UnavailableForLegalReasons
import com.javiersc.resources.networkResponse.NetworkResponse.ClientError.UnprocessableEntity
import com.javiersc.resources.networkResponse.NetworkResponse.ClientError.UnsupportedMediaType
import com.javiersc.resources.networkResponse.NetworkResponse.ClientError.UpgradeRequired
import com.javiersc.resources.networkResponse.NetworkResponse.ClientError.UriTooLong
import com.javiersc.resources.networkResponse.NetworkResponse.Info
import com.javiersc.resources.networkResponse.NetworkResponse.Info.Continue
import com.javiersc.resources.networkResponse.NetworkResponse.Info.EarlyHints
import com.javiersc.resources.networkResponse.NetworkResponse.Info.Processing
import com.javiersc.resources.networkResponse.NetworkResponse.Info.SwitchingProtocol
import com.javiersc.resources.networkResponse.NetworkResponse.InternetNotAvailable
import com.javiersc.resources.networkResponse.NetworkResponse.Redirection
import com.javiersc.resources.networkResponse.NetworkResponse.Redirection.Found
import com.javiersc.resources.networkResponse.NetworkResponse.Redirection.MovedPermanently
import com.javiersc.resources.networkResponse.NetworkResponse.Redirection.MultipleChoices
import com.javiersc.resources.networkResponse.NetworkResponse.Redirection.NotModified
import com.javiersc.resources.networkResponse.NetworkResponse.Redirection.PermanentRedirect
import com.javiersc.resources.networkResponse.NetworkResponse.Redirection.SeeOther
import com.javiersc.resources.networkResponse.NetworkResponse.Redirection.SwitchProxy
import com.javiersc.resources.networkResponse.NetworkResponse.Redirection.TemporaryRedirect
import com.javiersc.resources.networkResponse.NetworkResponse.Redirection.UseProxy
import com.javiersc.resources.networkResponse.NetworkResponse.RemoteError
import com.javiersc.resources.networkResponse.NetworkResponse.ServerError
import com.javiersc.resources.networkResponse.NetworkResponse.ServerError.BadGateway
import com.javiersc.resources.networkResponse.NetworkResponse.ServerError.GatewayTimeout
import com.javiersc.resources.networkResponse.NetworkResponse.ServerError.HttpVersionNotSupported
import com.javiersc.resources.networkResponse.NetworkResponse.ServerError.InsufficientStorage
import com.javiersc.resources.networkResponse.NetworkResponse.ServerError.InternalServerError
import com.javiersc.resources.networkResponse.NetworkResponse.ServerError.LoopDetected
import com.javiersc.resources.networkResponse.NetworkResponse.ServerError.NetworkAuthenticationRequired
import com.javiersc.resources.networkResponse.NetworkResponse.ServerError.NotExtended
import com.javiersc.resources.networkResponse.NetworkResponse.ServerError.NotImplemented
import com.javiersc.resources.networkResponse.NetworkResponse.ServerError.ServiceUnavailable
import com.javiersc.resources.networkResponse.NetworkResponse.ServerError.VariantAlsoNegotiates
import com.javiersc.resources.networkResponse.NetworkResponse.Success.Accepted
import com.javiersc.resources.networkResponse.NetworkResponse.Success.AlreadyReported
import com.javiersc.resources.networkResponse.NetworkResponse.Success.Created
import com.javiersc.resources.networkResponse.NetworkResponse.Success.Custom
import com.javiersc.resources.networkResponse.NetworkResponse.Success.ImUsed
import com.javiersc.resources.networkResponse.NetworkResponse.Success.MultiStatus
import com.javiersc.resources.networkResponse.NetworkResponse.Success.NoContent
import com.javiersc.resources.networkResponse.NetworkResponse.Success.NonAuthoritativeInformation
import com.javiersc.resources.networkResponse.NetworkResponse.Success.OK
import com.javiersc.resources.networkResponse.NetworkResponse.Success.PartialContent
import com.javiersc.resources.networkResponse.NetworkResponse.Success.ResetContent
import com.javiersc.resources.resource.Resource

@Suppress("LongParameterList", "ComplexMethod", "LongMethod")
inline fun <
        reified NR : Any,
        reified R : Any,
        reified ErDTO : Any?,
        reified Er : Any
        > NetworkResponse<NR, ErDTO>.toResource(
            crossinline success: (NR) -> R,
            crossinline error: (ErDTO?) -> Er,
            noinline clientError: ((ErDTO?) -> Er)? = null,
            noinline serverError: ((ErDTO?) -> Er)? = null,
            noinline badRequest: ((ErDTO?) -> Er)? = null,
            noinline unauthorized: ((ErDTO?) -> Er)? = null,
            noinline paymentRequired: ((ErDTO?) -> Er)? = null,
            noinline forbidden: ((ErDTO?) -> Er)? = null,
            noinline notFound: ((ErDTO?) -> Er)? = null,
            noinline methodNotAllowed: ((ErDTO?) -> Er)? = null,
            noinline notAcceptable: ((ErDTO?) -> Er)? = null,
            noinline proxyAuthenticationRequired: ((ErDTO?) -> Er)? = null,
            noinline requestTimeout: ((ErDTO?) -> Er)? = null,
            noinline conflict: ((ErDTO?) -> Er)? = null,
            noinline gone: ((ErDTO?) -> Er)? = null,
            noinline lengthRequired: ((ErDTO?) -> Er)? = null,
            noinline preconditionFailed: ((ErDTO?) -> Er)? = null,
            noinline payloadTooLarge: ((ErDTO?) -> Er)? = null,
            noinline uriTooLong: ((ErDTO?) -> Er)? = null,
            noinline unsupportedMediaType: ((ErDTO?) -> Er)? = null,
            noinline requestedRangeNotSatisfiable: ((ErDTO?) -> Er)? = null,
            noinline expectationFailed: ((ErDTO?) -> Er)? = null,
            noinline imATeapot: ((ErDTO?) -> Er)? = null,
            noinline misdirectedRequest: ((ErDTO?) -> Er)? = null,
            noinline unprocessableEntity: ((ErDTO?) -> Er)? = null,
            noinline locked: ((ErDTO?) -> Er)? = null,
            noinline failedDependency: ((ErDTO?) -> Er)? = null,
            noinline upgradeRequired: ((ErDTO?) -> Er)? = null,
            noinline preconditionRequired: ((ErDTO?) -> Er)? = null,
            noinline tooManyRequest: ((ErDTO?) -> Er)? = null,
            noinline requestHeaderFieldsTooLarge: ((ErDTO?) -> Er)? = null,
            noinline unavailableForLegalReasons: ((ErDTO?) -> Er)? = null,
            noinline customClientError: ((ErDTO?) -> Er)? = null,
            noinline internalServerError: ((ErDTO?) -> Er)? = null,
            noinline notImplemented: ((ErDTO?) -> Er)? = null,
            noinline badGateway: ((ErDTO?) -> Er)? = null,
            noinline serviceUnavailable: ((ErDTO?) -> Er)? = null,
            noinline gatewayTimeout: ((ErDTO?) -> Er)? = null,
            noinline httpVersionNotSupported: ((ErDTO?) -> Er)? = null,
            noinline variantAlsoNegotiates: ((ErDTO?) -> Er)? = null,
            noinline insufficientStorage: ((ErDTO?) -> Er)? = null,
            noinline loopDetected: ((ErDTO?) -> Er)? = null,
            noinline notExtended: ((ErDTO?) -> Er)? = null,
            noinline networkAuthenticationRequired: ((ErDTO?) -> Er)? = null,
            noinline customServerError: ((ErDTO?) -> Er)? = null,
            noinline customError: ((ErDTO?) -> Er)? = null,
            noinline internetNotAvailable: ((String?) -> Er)? = null,
            noinline remoteError: ((String?) -> Er)? = null
        ): Resource<R, Er> {
    return when (this) {
        is OK -> Resource.Success(success(value))
        is Created -> Resource.Success(success(value))
        is Accepted -> Resource.Success(success(value))
        is NonAuthoritativeInformation -> Resource.Success(success(value))
        is NoContent -> Resource.Success(null)
        is ResetContent -> Resource.Success(null)
        is PartialContent -> Resource.Success(success(value))
        is MultiStatus -> Resource.Success(success(value))
        is AlreadyReported -> Resource.Success(success(value))
        is ImUsed -> Resource.Success(success(value))
        is Custom -> Resource.Success(success(value))
        is ClientError.Custom -> Resource.Error(
            customClientError?.invoke(this.error)
                ?: serverError?.invoke(this.error)
                ?: error(this.error)
        )
        is BadRequest -> Resource.Error(
            badRequest?.invoke(this.error)
                ?: clientError?.invoke(this.error)
                ?: error(this.error)
        )
        is Unauthorized -> Resource.Error(
            unauthorized?.invoke(this.error)
                ?: clientError?.invoke(this.error)
                ?: error(this.error)
        )
        is PaymentRequired -> Resource.Error(
            paymentRequired?.invoke(this.error)
                ?: clientError?.invoke(this.error)
                ?: error(this.error)
        )
        is Forbidden -> Resource.Error(
            forbidden?.invoke(this.error)
                ?: clientError?.invoke(this.error)
                ?: error(this.error)
        )
        is NotFound -> Resource.Error(
            notFound?.invoke(this.error)
                ?: clientError?.invoke(this.error)
                ?: error(this.error)
        )
        is MethodNotAllowed -> Resource.Error(
            methodNotAllowed?.invoke(this.error)
                ?: clientError?.invoke(this.error)
                ?: error(this.error)
        )
        is NotAcceptable -> Resource.Error(
            notAcceptable?.invoke(this.error)
                ?: clientError?.invoke(this.error)
                ?: error(this.error)
        )
        is ProxyAuthenticationRequired -> Resource.Error(
            proxyAuthenticationRequired?.invoke(this.error)
                ?: clientError?.invoke(this.error)
                ?: error(this.error)
        )
        is RequestTimeout -> Resource.Error(
            requestTimeout?.invoke(this.error)
                ?: clientError?.invoke(this.error)
                ?: error(this.error)
        )
        is Conflict -> Resource.Error(
            conflict?.invoke(this.error)
                ?: clientError?.invoke(this.error)
                ?: error(this.error)
        )
        is Gone -> Resource.Error(
            gone?.invoke(this.error)
                ?: clientError?.invoke(this.error)
                ?: error(this.error)
        )
        is LengthRequired -> Resource.Error(
            lengthRequired?.invoke(this.error)
                ?: clientError?.invoke(this.error)
                ?: error(this.error)
        )
        is PreconditionFailed -> Resource.Error(
            preconditionFailed?.invoke(this.error)
                ?: clientError?.invoke(this.error)
                ?: error(this.error)
        )
        is PayloadTooLarge -> Resource.Error(
            payloadTooLarge?.invoke(this.error)
                ?: clientError?.invoke(this.error)
                ?: error(this.error)
        )
        is UriTooLong -> Resource.Error(
            uriTooLong?.invoke(this.error)
                ?: clientError?.invoke(this.error)
                ?: error(this.error)
        )
        is UnsupportedMediaType -> Resource.Error(
            unsupportedMediaType?.invoke(this.error)
                ?: clientError?.invoke(this.error)
                ?: error(this.error)
        )
        is RequestedRangeNotSatisfiable -> Resource.Error(
            requestedRangeNotSatisfiable?.invoke(this.error)
                ?: clientError?.invoke(this.error)
                ?: error(this.error)
        )
        is ExpectationFailed -> Resource.Error(
            expectationFailed?.invoke(this.error)
                ?: clientError?.invoke(this.error)
                ?: error(this.error)
        )
        is ImATeapot -> Resource.Error(
            imATeapot?.invoke(this.error)
                ?: clientError?.invoke(this.error)
                ?: error(this.error)
        )
        is MisdirectedRequest -> Resource.Error(
            misdirectedRequest?.invoke(this.error)
                ?: clientError?.invoke(this.error)
                ?: error(this.error)
        )
        is UnprocessableEntity -> Resource.Error(
            unprocessableEntity?.invoke(this.error)
                ?: clientError?.invoke(this.error)
                ?: error(this.error)
        )
        is Locked -> Resource.Error(
            locked?.invoke(this.error)
                ?: clientError?.invoke(this.error)
                ?: error(this.error)
        )
        is FailedDependency -> Resource.Error(
            failedDependency?.invoke(this.error)
                ?: clientError?.invoke(this.error)
                ?: error(this.error)
        )
        is UpgradeRequired -> Resource.Error(
            upgradeRequired?.invoke(this.error)
                ?: clientError?.invoke(this.error)
                ?: error(this.error)
        )
        is PreconditionRequired -> Resource.Error(
            preconditionRequired?.invoke(this.error)
                ?: clientError?.invoke(this.error)
                ?: error(this.error)
        )
        is TooManyRequest -> Resource.Error(
            tooManyRequest?.invoke(this.error)
                ?: clientError?.invoke(this.error)
                ?: error(this.error)
        )
        is RequestHeaderFieldsTooLarge -> Resource.Error(
            requestHeaderFieldsTooLarge?.invoke(this.error)
                ?: clientError?.invoke(this.error)
                ?: error(this.error)
        )
        is UnavailableForLegalReasons -> Resource.Error(
            unavailableForLegalReasons?.invoke(this.error)
                ?: clientError?.invoke(this.error)
                ?: error(this.error)
        )
        is ServerError.Custom -> Resource.Error(
            customServerError?.invoke(this.error)
                ?: serverError?.invoke(this.error)
                ?: error(this.error)
        )
        is InternalServerError -> Resource.Error(
            internalServerError?.invoke(this.error)
                ?: serverError?.invoke(this.error)
                ?: error(this.error)
        )
        is NotImplemented -> Resource.Error(
            notImplemented?.invoke(this.error)
                ?: serverError?.invoke(this.error)
                ?: error(this.error)
        )
        is BadGateway -> Resource.Error(
            badGateway?.invoke(this.error)
                ?: serverError?.invoke(this.error)
                ?: error(this.error)
        )
        is ServiceUnavailable -> Resource.Error(
            serviceUnavailable?.invoke(this.error)
                ?: serverError?.invoke(this.error)
                ?: error(this.error)
        )
        is GatewayTimeout -> Resource.Error(
            gatewayTimeout?.invoke(this.error)
                ?: serverError?.invoke(this.error)
                ?: error(this.error)
        )
        is HttpVersionNotSupported -> Resource.Error(
            httpVersionNotSupported?.invoke(this.error)
                ?: serverError?.invoke(this.error)
                ?: error(this.error)
        )
        is VariantAlsoNegotiates -> Resource.Error(
            variantAlsoNegotiates?.invoke(this.error)
                ?: serverError?.invoke(this.error)
                ?: error(this.error)
        )
        is InsufficientStorage -> Resource.Error(
            insufficientStorage?.invoke(this.error)
                ?: serverError?.invoke(this.error)
                ?: error(this.error)
        )
        is LoopDetected -> Resource.Error(
            loopDetected?.invoke(this.error)
                ?: serverError?.invoke(this.error)
                ?: error(this.error)
        )
        is NotExtended -> Resource.Error(
            notExtended?.invoke(this.error)
                ?: serverError?.invoke(this.error)
                ?: error(this.error)
        )
        is NetworkAuthenticationRequired -> Resource.Error(
            networkAuthenticationRequired?.invoke(this.error)
                ?: serverError?.invoke(this.error)
                ?: error(this.error)
        )
        is NetworkResponse.CustomError ->
            Resource.Error(customError?.invoke(this.error) ?: error(this.error))
        is InternetNotAvailable -> Resource.Error(internetNotAvailable?.invoke(this.error))
        is RemoteError -> Resource.Error(remoteError?.invoke(this.error))
        is Continue -> TODO()
        is SwitchingProtocol -> TODO()
        is Processing -> TODO()
        is EarlyHints -> TODO()
        is Info.Custom -> TODO()
        is MultipleChoices -> TODO()
        is MovedPermanently -> TODO()
        is Found -> TODO()
        is SeeOther -> TODO()
        is NotModified -> TODO()
        is UseProxy -> TODO()
        is SwitchProxy -> TODO()
        is TemporaryRedirect -> TODO()
        is PermanentRedirect -> TODO()
        is Redirection.Custom -> TODO()
    }
}
