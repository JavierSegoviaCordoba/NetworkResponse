package com.javiersc.resources.networkResponse.extensions

import com.javiersc.resources.networkResponse.NetworkResponse
import com.javiersc.resources.networkResponse.NetworkResponse.*
import com.javiersc.resources.networkResponse.NetworkResponse.ClientError.*
import com.javiersc.resources.networkResponse.NetworkResponse.Redirection.*
import com.javiersc.resources.networkResponse.NetworkResponse.ServerError.*
import com.javiersc.resources.networkResponse.NetworkResponse.Success.*
import com.javiersc.resources.resource.Resource

inline fun <reified NR : Any,
        reified R : Any,
        reified ErDTO : Any?,
        reified Er : Any
        > NetworkResponse<NR, ErDTO>.toResource(
            crossinline success: (NR) -> R,
            crossinline error: (ErDTO?) -> Er,
            noinline mapNonGenericSuccess: ((NR?) -> R)? = null,
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
            noinline mapNonGenericError: ((ErDTO?) -> Er)? = null,
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
        is NonGenericSuccess -> Resource.Success(mapNonGenericSuccess?.invoke(resource))
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
        is URITooLong -> Resource.Error(
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
        is HTTPVersionNotSupported -> Resource.Error(
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
        is NonGenericError -> Resource.Error(mapNonGenericError?.invoke(this.error) ?: error(
            this.error
        ))
        is InternetNotAvailable -> Resource.Error(mapInternetNotAvailable?.invoke(this.error))
        is RemoteError -> Resource.Error(mapRemoteError?.invoke(this.error))
        is Info.Any -> TODO()
        is Info.Continue -> TODO()
        is Info.SwitchingProtocol -> TODO()
        is Info.Processing -> TODO()
        is Success.Any -> TODO()
        is Redirection.Any -> TODO()
        is MultipleChoices -> TODO()
        is MovedPermanently -> TODO()
        is Found -> TODO()
        is SeeOther -> TODO()
        is NotModified -> TODO()
        is UseProxy -> TODO()
        is SwitchProxy -> TODO()
        is TemporaryRedirect -> TODO()
        is PermanentRedirect -> TODO()
        is ClientError.Any -> TODO()
        is ServerError.Any -> TODO()
    }
}
