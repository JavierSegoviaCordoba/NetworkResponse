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
inline fun <reified NR : Any,
        reified R : Any,
        reified ErDTO : Any?,
        reified Er : Any
        > NetworkResponse<NR, ErDTO>.toResource(
            crossinline success: (NR) -> R,
            crossinline error: (ErDTO?) -> Er,
            noinline mapClientError: ((ErDTO?) -> Er)? = null,
            noinline mapServerError: ((ErDTO?) -> Er)? = null,
            noinline mapBadRequest: ((ErDTO?) -> Er)? = null,
            noinline mapUnauthorized: ((ErDTO?) -> Er)? = null,
            noinline mapPaymentRequired: ((ErDTO?) -> Er)? = null,
            noinline mapForbidden: ((ErDTO?) -> Er)? = null,
            noinline mapNotFound: ((ErDTO?) -> Er)? = null,
            noinline mapMethodNotAllowed: ((ErDTO?) -> Er)? = null,
            noinline mapNotAcceptable: ((ErDTO?) -> Er)? = null,
            noinline mapProxyAuthenticationRequired: ((ErDTO?) -> Er)? = null,
            noinline mapRequestTimeout: ((ErDTO?) -> Er)? = null,
            noinline mapConflict: ((ErDTO?) -> Er)? = null,
            noinline mapGone: ((ErDTO?) -> Er)? = null,
            noinline mapLengthRequired: ((ErDTO?) -> Er)? = null,
            noinline mapPreconditionFailed: ((ErDTO?) -> Er)? = null,
            noinline mapPayloadTooLarge: ((ErDTO?) -> Er)? = null,
            noinline mapURITooLong: ((ErDTO?) -> Er)? = null,
            noinline mapUnsupportedMediaType: ((ErDTO?) -> Er)? = null,
            noinline mapRequestedRangeNotSatisfiable: ((ErDTO?) -> Er)? = null,
            noinline mapExpectationFailed: ((ErDTO?) -> Er)? = null,
            noinline mapImATeapot: ((ErDTO?) -> Er)? = null,
            noinline mapMisdirectedRequest: ((ErDTO?) -> Er)? = null,
            noinline mapUnprocessableEntity: ((ErDTO?) -> Er)? = null,
            noinline mapLocked: ((ErDTO?) -> Er)? = null,
            noinline mapFailedDependency: ((ErDTO?) -> Er)? = null,
            noinline mapUpgradeRequired: ((ErDTO?) -> Er)? = null,
            noinline mapPreconditionRequired: ((ErDTO?) -> Er)? = null,
            noinline mapTooManyRequest: ((ErDTO?) -> Er)? = null,
            noinline mapRequestHeaderFieldsTooLarge: ((ErDTO?) -> Er)? = null,
            noinline mapUnavailableForLegalReasons: ((ErDTO?) -> Er)? = null,
            noinline mapCustomClientError: ((ErDTO?) -> Er)? = null,
            noinline mapInternalServerError: ((ErDTO?) -> Er)? = null,
            noinline mapNotImplemented: ((ErDTO?) -> Er)? = null,
            noinline mapBadGateway: ((ErDTO?) -> Er)? = null,
            noinline mapServiceUnavailable: ((ErDTO?) -> Er)? = null,
            noinline mapGatewayTimeout: ((ErDTO?) -> Er)? = null,
            noinline mapHTTPVersionNotSupported: ((ErDTO?) -> Er)? = null,
            noinline mapVariantAlsoNegotiates: ((ErDTO?) -> Er)? = null,
            noinline mapInsufficientStorage: ((ErDTO?) -> Er)? = null,
            noinline mapLoopDetected: ((ErDTO?) -> Er)? = null,
            noinline mapNotExtended: ((ErDTO?) -> Er)? = null,
            noinline mapNetworkAuthenticationRequired: ((ErDTO?) -> Er)? = null,
            noinline mapCustomServerError: ((ErDTO?) -> Er)? = null,
            noinline mapCustomError: ((ErDTO?) -> Er)? = null,
            noinline mapInternetNotAvailable: ((String?) -> Er)? = null,
            noinline mapRemoteError: ((String?) -> Er)? = null
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
            mapCustomClientError?.invoke(this.error)
                ?: mapServerError?.invoke(this.error)
                ?: error(this.error)
        )
        is BadRequest -> Resource.Error(
            mapBadRequest?.invoke(this.error)
                ?: mapClientError?.invoke(this.error)
                ?: error(this.error)
        )
        is Unauthorized -> Resource.Error(
            mapUnauthorized?.invoke(this.error)
                ?: mapClientError?.invoke(this.error)
                ?: error(this.error)
        )
        is PaymentRequired -> Resource.Error(
            mapPaymentRequired?.invoke(this.error)
                ?: mapClientError?.invoke(this.error)
                ?: error(this.error)
        )
        is Forbidden -> Resource.Error(
            mapForbidden?.invoke(this.error)
                ?: mapClientError?.invoke(this.error)
                ?: error(this.error)
        )
        is NotFound -> Resource.Error(
            mapNotFound?.invoke(this.error)
                ?: mapClientError?.invoke(this.error)
                ?: error(this.error)
        )
        is MethodNotAllowed -> Resource.Error(
            mapMethodNotAllowed?.invoke(this.error)
                ?: mapClientError?.invoke(this.error)
                ?: error(this.error)
        )
        is NotAcceptable -> Resource.Error(
            mapNotAcceptable?.invoke(this.error)
                ?: mapClientError?.invoke(this.error)
                ?: error(this.error)
        )
        is ProxyAuthenticationRequired -> Resource.Error(
            mapProxyAuthenticationRequired?.invoke(this.error)
                ?: mapClientError?.invoke(this.error)
                ?: error(this.error)
        )
        is RequestTimeout -> Resource.Error(
            mapRequestTimeout?.invoke(this.error)
                ?: mapClientError?.invoke(this.error)
                ?: error(this.error)
        )
        is Conflict -> Resource.Error(
            mapConflict?.invoke(this.error)
                ?: mapClientError?.invoke(this.error)
                ?: error(this.error)
        )
        is Gone -> Resource.Error(
            mapGone?.invoke(this.error)
                ?: mapClientError?.invoke(this.error)
                ?: error(this.error)
        )
        is LengthRequired -> Resource.Error(
            mapLengthRequired?.invoke(this.error)
                ?: mapClientError?.invoke(this.error)
                ?: error(this.error)
        )
        is PreconditionFailed -> Resource.Error(
            mapPreconditionFailed?.invoke(this.error)
                ?: mapClientError?.invoke(this.error)
                ?: error(this.error)
        )
        is PayloadTooLarge -> Resource.Error(
            mapPayloadTooLarge?.invoke(this.error)
                ?: mapClientError?.invoke(this.error)
                ?: error(this.error)
        )
        is UriTooLong -> Resource.Error(
            mapURITooLong?.invoke(this.error)
                ?: mapClientError?.invoke(this.error)
                ?: error(this.error)
        )
        is UnsupportedMediaType -> Resource.Error(
            mapUnsupportedMediaType?.invoke(this.error)
                ?: mapClientError?.invoke(this.error)
                ?: error(this.error)
        )
        is RequestedRangeNotSatisfiable -> Resource.Error(
            mapRequestedRangeNotSatisfiable?.invoke(this.error)
                ?: mapClientError?.invoke(this.error)
                ?: error(this.error)
        )
        is ExpectationFailed -> Resource.Error(
            mapExpectationFailed?.invoke(this.error)
                ?: mapClientError?.invoke(this.error)
                ?: error(this.error)
        )
        is ImATeapot -> Resource.Error(
            mapImATeapot?.invoke(this.error)
                ?: mapClientError?.invoke(this.error)
                ?: error(this.error)
        )
        is MisdirectedRequest -> Resource.Error(
            mapMisdirectedRequest?.invoke(this.error)
                ?: mapClientError?.invoke(this.error)
                ?: error(this.error)
        )
        is UnprocessableEntity -> Resource.Error(
            mapUnprocessableEntity?.invoke(this.error)
                ?: mapClientError?.invoke(this.error)
                ?: error(this.error)
        )
        is Locked -> Resource.Error(
            mapLocked?.invoke(this.error)
                ?: mapClientError?.invoke(this.error)
                ?: error(this.error)
        )
        is FailedDependency -> Resource.Error(
            mapFailedDependency?.invoke(this.error)
                ?: mapClientError?.invoke(this.error)
                ?: error(this.error)
        )
        is UpgradeRequired -> Resource.Error(
            mapUpgradeRequired?.invoke(this.error)
                ?: mapClientError?.invoke(this.error)
                ?: error(this.error)
        )
        is PreconditionRequired -> Resource.Error(
            mapPreconditionRequired?.invoke(this.error)
                ?: mapClientError?.invoke(this.error)
                ?: error(this.error)
        )
        is TooManyRequest -> Resource.Error(
            mapTooManyRequest?.invoke(this.error)
                ?: mapClientError?.invoke(this.error)
                ?: error(this.error)
        )
        is RequestHeaderFieldsTooLarge -> Resource.Error(
            mapRequestHeaderFieldsTooLarge?.invoke(this.error)
                ?: mapClientError?.invoke(this.error)
                ?: error(this.error)
        )
        is UnavailableForLegalReasons -> Resource.Error(
            mapUnavailableForLegalReasons?.invoke(this.error)
                ?: mapClientError?.invoke(this.error)
                ?: error(this.error)
        )
        is ServerError.Custom -> Resource.Error(
            mapCustomServerError?.invoke(this.error)
                ?: mapServerError?.invoke(this.error)
                ?: error(this.error)
        )
        is InternalServerError -> Resource.Error(
            mapInternalServerError?.invoke(this.error)
                ?: mapServerError?.invoke(this.error)
                ?: error(this.error)
        )
        is NotImplemented -> Resource.Error(
            mapNotImplemented?.invoke(this.error)
                ?: mapServerError?.invoke(this.error)
                ?: error(this.error)
        )
        is BadGateway -> Resource.Error(
            mapBadGateway?.invoke(this.error)
                ?: mapServerError?.invoke(this.error)
                ?: error(this.error)
        )
        is ServiceUnavailable -> Resource.Error(
            mapServiceUnavailable?.invoke(this.error)
                ?: mapServerError?.invoke(this.error)
                ?: error(this.error)
        )
        is GatewayTimeout -> Resource.Error(
            mapGatewayTimeout?.invoke(this.error)
                ?: mapServerError?.invoke(this.error)
                ?: error(this.error)
        )
        is HttpVersionNotSupported -> Resource.Error(
            mapHTTPVersionNotSupported?.invoke(this.error)
                ?: mapServerError?.invoke(this.error)
                ?: error(this.error)
        )
        is VariantAlsoNegotiates -> Resource.Error(
            mapVariantAlsoNegotiates?.invoke(this.error)
                ?: mapServerError?.invoke(this.error)
                ?: error(this.error)
        )
        is InsufficientStorage -> Resource.Error(
            mapInsufficientStorage?.invoke(this.error)
                ?: mapServerError?.invoke(this.error)
                ?: error(this.error)
        )
        is LoopDetected -> Resource.Error(
            mapLoopDetected?.invoke(this.error)
                ?: mapServerError?.invoke(this.error)
                ?: error(this.error)
        )
        is NotExtended -> Resource.Error(
            mapNotExtended?.invoke(this.error)
                ?: mapServerError?.invoke(this.error)
                ?: error(this.error)
        )
        is NetworkAuthenticationRequired -> Resource.Error(
            mapNetworkAuthenticationRequired?.invoke(this.error)
                ?: mapServerError?.invoke(this.error)
                ?: error(this.error)
        )
        is NetworkResponse.CustomError ->
            Resource.Error(mapCustomError?.invoke(this.error) ?: error(this.error))
        is InternetNotAvailable -> Resource.Error(mapInternetNotAvailable?.invoke(this.error))
        is RemoteError -> Resource.Error(mapRemoteError?.invoke(this.error))
        is Info.Continue -> TODO()
        is Info.SwitchingProtocol -> TODO()
        is Info.Processing -> TODO()
        is Info.EarlyHints -> TODO()
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
