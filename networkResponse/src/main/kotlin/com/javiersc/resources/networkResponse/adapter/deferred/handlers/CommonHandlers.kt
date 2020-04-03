package com.javiersc.resources.networkResponse.adapter.deferred.handlers

import com.javiersc.resources.networkResponse.NetworkResponse
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
import com.javiersc.resources.networkResponse.NetworkResponse.Info.Continue
import com.javiersc.resources.networkResponse.NetworkResponse.Info.EarlyHints
import com.javiersc.resources.networkResponse.NetworkResponse.Info.Processing
import com.javiersc.resources.networkResponse.NetworkResponse.Info.SwitchingProtocol
import com.javiersc.resources.networkResponse.NetworkResponse.Redirection.Found
import com.javiersc.resources.networkResponse.NetworkResponse.Redirection.MovedPermanently
import com.javiersc.resources.networkResponse.NetworkResponse.Redirection.MultipleChoices
import com.javiersc.resources.networkResponse.NetworkResponse.Redirection.NotModified
import com.javiersc.resources.networkResponse.NetworkResponse.Redirection.PermanentRedirect
import com.javiersc.resources.networkResponse.NetworkResponse.Redirection.SeeOther
import com.javiersc.resources.networkResponse.NetworkResponse.Redirection.SwitchProxy
import com.javiersc.resources.networkResponse.NetworkResponse.Redirection.TemporaryRedirect
import com.javiersc.resources.networkResponse.NetworkResponse.Redirection.UseProxy
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
import com.javiersc.resources.networkResponse.StatusCode.ACCEPTED
import com.javiersc.resources.networkResponse.StatusCode.ALREADY_REPORTED
import com.javiersc.resources.networkResponse.StatusCode.BAD_GATEWAY
import com.javiersc.resources.networkResponse.StatusCode.BAD_REQUEST
import com.javiersc.resources.networkResponse.StatusCode.CONFLICT
import com.javiersc.resources.networkResponse.StatusCode.CONTINUE_100
import com.javiersc.resources.networkResponse.StatusCode.CREATED
import com.javiersc.resources.networkResponse.StatusCode.EARLY_HINTS_103
import com.javiersc.resources.networkResponse.StatusCode.EXPECTATION_FAILED
import com.javiersc.resources.networkResponse.StatusCode.FAILED_DEPENDENCY
import com.javiersc.resources.networkResponse.StatusCode.FORBIDDEN
import com.javiersc.resources.networkResponse.StatusCode.FOUND_302
import com.javiersc.resources.networkResponse.StatusCode.GATEWAY_TIMEOUT
import com.javiersc.resources.networkResponse.StatusCode.GONE
import com.javiersc.resources.networkResponse.StatusCode.HTTP_VERSION_NOT_SUPPORTED
import com.javiersc.resources.networkResponse.StatusCode.IM_A_TEAPOT
import com.javiersc.resources.networkResponse.StatusCode.IM_USED
import com.javiersc.resources.networkResponse.StatusCode.INSUFFICIENT_STORAGE
import com.javiersc.resources.networkResponse.StatusCode.INTERNAL_SERVER_ERROR
import com.javiersc.resources.networkResponse.StatusCode.LENGTH_REQUIRED
import com.javiersc.resources.networkResponse.StatusCode.LOCKED
import com.javiersc.resources.networkResponse.StatusCode.LOOP_DETECTED
import com.javiersc.resources.networkResponse.StatusCode.METHOD_NOT_ALLOWED
import com.javiersc.resources.networkResponse.StatusCode.MISDIRECTED_REQUEST
import com.javiersc.resources.networkResponse.StatusCode.MOVED_PERMANENTLY_301
import com.javiersc.resources.networkResponse.StatusCode.MULTIPLE_CHOICE_300
import com.javiersc.resources.networkResponse.StatusCode.MULTI_STATUS
import com.javiersc.resources.networkResponse.StatusCode.NETWORK_AUTHENTICATION_REQUIRED
import com.javiersc.resources.networkResponse.StatusCode.NON_AUTHORITATIVE_INFORMATION
import com.javiersc.resources.networkResponse.StatusCode.NOT_ACCEPTABLE
import com.javiersc.resources.networkResponse.StatusCode.NOT_EXTENDED
import com.javiersc.resources.networkResponse.StatusCode.NOT_FOUND
import com.javiersc.resources.networkResponse.StatusCode.NOT_IMPLEMENTED
import com.javiersc.resources.networkResponse.StatusCode.NOT_MODIFIED_304
import com.javiersc.resources.networkResponse.StatusCode.NO_CONTENT
import com.javiersc.resources.networkResponse.StatusCode.OK
import com.javiersc.resources.networkResponse.StatusCode.PARTIAL_CONTENT
import com.javiersc.resources.networkResponse.StatusCode.PAYLOAD_TOO_LARGE
import com.javiersc.resources.networkResponse.StatusCode.PAYMENT_REQUIRED
import com.javiersc.resources.networkResponse.StatusCode.PERMANENT_REDIRECT_308
import com.javiersc.resources.networkResponse.StatusCode.PRECONDITION_FAILED
import com.javiersc.resources.networkResponse.StatusCode.PRECONDITION_REQUIRED
import com.javiersc.resources.networkResponse.StatusCode.PROCESSING_102
import com.javiersc.resources.networkResponse.StatusCode.PROXY_AUTHENTICATION_REQUIRED
import com.javiersc.resources.networkResponse.StatusCode.REQUESTED_RANGE_NOT_SATISFIABLE
import com.javiersc.resources.networkResponse.StatusCode.REQUEST_HEADER_FIELDS_TOO_LARGE
import com.javiersc.resources.networkResponse.StatusCode.REQUEST_TIMEOUT
import com.javiersc.resources.networkResponse.StatusCode.RESET_CONTENT
import com.javiersc.resources.networkResponse.StatusCode.SEE_OTHER_303
import com.javiersc.resources.networkResponse.StatusCode.SERVICE_UNAVAILABLE
import com.javiersc.resources.networkResponse.StatusCode.SWITCHING_PROTOCOL_101
import com.javiersc.resources.networkResponse.StatusCode.SWITCH_PROXY_306
import com.javiersc.resources.networkResponse.StatusCode.TEMPORARY_REDIRECT_307
import com.javiersc.resources.networkResponse.StatusCode.TOO_MANY_REQUEST
import com.javiersc.resources.networkResponse.StatusCode.UNAUTHORIZED
import com.javiersc.resources.networkResponse.StatusCode.UNAVAILABLE_FOR_LEGAL_REASONS
import com.javiersc.resources.networkResponse.StatusCode.UNPROCESSABLE_ENTITY
import com.javiersc.resources.networkResponse.StatusCode.UNSUPPORTED_MEDIA_TYPE
import com.javiersc.resources.networkResponse.StatusCode.UPGRADE_REQUIRED
import com.javiersc.resources.networkResponse.StatusCode.URI_TOO_LONG
import com.javiersc.resources.networkResponse.StatusCode.USE_PROXY_305
import com.javiersc.resources.networkResponse.StatusCode.VARIANT_ALSO_NEGOTIATES
import kotlinx.coroutines.CompletableDeferred
import okhttp3.Headers

internal fun <R, E> handleAllNoSuccess(
    deferred: CompletableDeferred<NetworkResponse<R, E>>,
    code: Int,
    errorBody: E?,
    headers: Headers?
) {
    @Suppress("MagicNumber")
    when (code) {
        in 100..199 -> handle1xx(deferred, code, headers)
        in 300..399 -> handle3xx(deferred, code, headers)
        in 400..499 -> handle4xx(deferred, code, errorBody, headers)
        in 500..599 -> handle5xx(deferred, code, errorBody, headers)
    }
}

internal fun <R, E> handle1xx(
    deferred: CompletableDeferred<NetworkResponse<R, E>>,
    code: Int,
    headers: Headers?
) = with(deferred) {
    when (code) {
        CONTINUE_100 -> complete(Continue(headers))
        SWITCHING_PROTOCOL_101 -> complete(SwitchingProtocol(headers))
        PROCESSING_102 -> complete(Processing(headers))
        EARLY_HINTS_103 -> complete(EarlyHints(headers))
        else -> deferred.complete(NetworkResponse.Info.Custom(code, headers))
    }
}

internal fun <R, E> handle2xx(
    deferred: CompletableDeferred<NetworkResponse<R, E>>,
    code: Int,
    body: R,
    headers: Headers?
) = with(deferred) {
    when (code) {
        OK -> complete(OK(body, headers))
        CREATED -> complete(Created(body, headers))
        ACCEPTED -> complete(Accepted(body, headers))
        NON_AUTHORITATIVE_INFORMATION -> complete(NonAuthoritativeInformation(body, headers))
        NO_CONTENT -> complete(NoContent(headers))
        RESET_CONTENT -> complete(ResetContent(headers))
        PARTIAL_CONTENT -> complete(PartialContent(body, headers))
        MULTI_STATUS -> complete(MultiStatus(body, headers))
        ALREADY_REPORTED -> complete(AlreadyReported(body, headers))
        IM_USED -> complete(ImUsed(body, headers))
        else -> complete(Custom(body, code, headers))
    }
}

internal fun <R, E> handle3xx(
    deferred: CompletableDeferred<NetworkResponse<R, E>>,
    code: Int,
    headers: Headers?
) = with(deferred) {
    when (code) {
        MULTIPLE_CHOICE_300 -> complete(MultipleChoices(headers))
        MOVED_PERMANENTLY_301 -> complete(MovedPermanently(headers))
        FOUND_302 -> complete(Found(headers))
        SEE_OTHER_303 -> complete(SeeOther(headers))
        NOT_MODIFIED_304 -> complete(NotModified(headers))
        USE_PROXY_305 -> complete(UseProxy(headers))
        SWITCH_PROXY_306 -> complete(SwitchProxy(headers))
        TEMPORARY_REDIRECT_307 -> complete(TemporaryRedirect(headers))
        PERMANENT_REDIRECT_308 -> complete(PermanentRedirect(headers))
        else -> deferred.complete(NetworkResponse.Redirection.Custom(code, headers))
    }
}

@Suppress("ComplexMethod")
internal fun <R, E> handle4xx(
    deferred: CompletableDeferred<NetworkResponse<R, E>>,
    code: Int,
    errorBody: E?,
    headers: Headers?
) = with(deferred) {
    when (code) {
        BAD_REQUEST -> complete(BadRequest(errorBody, headers))
        UNAUTHORIZED -> complete(Unauthorized(errorBody, headers))
        PAYMENT_REQUIRED -> complete(PaymentRequired(errorBody, headers))
        FORBIDDEN -> complete(Forbidden(errorBody, headers))
        NOT_FOUND -> complete(NotFound(errorBody, headers))
        METHOD_NOT_ALLOWED -> complete(MethodNotAllowed(errorBody, headers))
        NOT_ACCEPTABLE -> complete(NotAcceptable(errorBody, headers))
        PROXY_AUTHENTICATION_REQUIRED -> complete(ProxyAuthenticationRequired(errorBody, headers))
        REQUEST_TIMEOUT -> complete(RequestTimeout(errorBody, headers))
        CONFLICT -> complete(Conflict(errorBody, headers))
        GONE -> complete(Gone(errorBody, headers))
        LENGTH_REQUIRED -> complete(LengthRequired(errorBody, headers))
        PRECONDITION_FAILED -> complete(PreconditionFailed(errorBody, headers))
        PAYLOAD_TOO_LARGE -> complete(PayloadTooLarge(errorBody, headers))
        URI_TOO_LONG -> complete(UriTooLong(errorBody, headers))
        UNSUPPORTED_MEDIA_TYPE -> complete(UnsupportedMediaType(errorBody, headers))
        REQUESTED_RANGE_NOT_SATISFIABLE ->
            complete(RequestedRangeNotSatisfiable(errorBody, headers))
        EXPECTATION_FAILED -> complete(ExpectationFailed(errorBody, headers))
        IM_A_TEAPOT -> complete(ImATeapot(errorBody, headers))
        MISDIRECTED_REQUEST -> complete(MisdirectedRequest(errorBody, headers))
        UNPROCESSABLE_ENTITY -> complete(UnprocessableEntity(errorBody, headers))
        LOCKED -> complete(Locked(errorBody, headers))
        FAILED_DEPENDENCY -> complete(FailedDependency(errorBody, headers))
        UPGRADE_REQUIRED -> complete(UpgradeRequired(errorBody, headers))
        PRECONDITION_REQUIRED -> complete(PreconditionRequired(errorBody, headers))
        TOO_MANY_REQUEST -> complete(TooManyRequest(errorBody, headers))
        REQUEST_HEADER_FIELDS_TOO_LARGE -> complete(RequestHeaderFieldsTooLarge(errorBody, headers))
        UNAVAILABLE_FOR_LEGAL_REASONS -> complete(UnavailableForLegalReasons(errorBody, headers))
        else -> deferred.complete(NetworkResponse.ClientError.Custom(errorBody, code, headers))
    }
}

@Suppress("ComplexMethod")
internal fun <R, E> handle5xx(
    deferred: CompletableDeferred<NetworkResponse<R, E>>,
    code: Int,
    errorBody: E?,
    headers: Headers?
) = with(deferred) {
    when (code) {
        INTERNAL_SERVER_ERROR -> complete(InternalServerError(errorBody, headers))
        NOT_IMPLEMENTED -> complete(NotImplemented(errorBody, headers))
        BAD_GATEWAY -> complete(BadGateway(errorBody, headers))
        SERVICE_UNAVAILABLE -> complete(ServiceUnavailable(errorBody, headers))
        GATEWAY_TIMEOUT -> complete(GatewayTimeout(errorBody, headers))
        HTTP_VERSION_NOT_SUPPORTED -> complete(HttpVersionNotSupported(errorBody, headers))
        VARIANT_ALSO_NEGOTIATES -> complete(VariantAlsoNegotiates(errorBody, headers))
        INSUFFICIENT_STORAGE -> complete(InsufficientStorage(errorBody, headers))
        LOOP_DETECTED -> complete(LoopDetected(errorBody, headers))
        NOT_EXTENDED -> complete(NotExtended(errorBody, headers))
        NETWORK_AUTHENTICATION_REQUIRED ->
            complete(NetworkAuthenticationRequired(errorBody, headers))
        else -> deferred.complete(NetworkResponse.ServerError.Custom(errorBody, code, headers))
    }
}
