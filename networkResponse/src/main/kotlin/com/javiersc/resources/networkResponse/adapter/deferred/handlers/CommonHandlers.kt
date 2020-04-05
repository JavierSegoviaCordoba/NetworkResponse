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
import com.javiersc.resources.networkResponse.StatusCode.ACCEPTED_202
import com.javiersc.resources.networkResponse.StatusCode.ALREADY_REPORTED_208
import com.javiersc.resources.networkResponse.StatusCode.BAD_GATEWAY
import com.javiersc.resources.networkResponse.StatusCode.BAD_REQUEST_400
import com.javiersc.resources.networkResponse.StatusCode.CONFLICT_409
import com.javiersc.resources.networkResponse.StatusCode.CONTINUE_100
import com.javiersc.resources.networkResponse.StatusCode.CREATED_201
import com.javiersc.resources.networkResponse.StatusCode.EARLY_HINTS_103
import com.javiersc.resources.networkResponse.StatusCode.EXPECTATION_FAILED_417
import com.javiersc.resources.networkResponse.StatusCode.FAILED_DEPENDENCY_424
import com.javiersc.resources.networkResponse.StatusCode.FORBIDDEN_403
import com.javiersc.resources.networkResponse.StatusCode.FOUND_302
import com.javiersc.resources.networkResponse.StatusCode.GATEWAY_TIMEOUT
import com.javiersc.resources.networkResponse.StatusCode.GONE_410
import com.javiersc.resources.networkResponse.StatusCode.HTTP_VERSION_NOT_SUPPORTED
import com.javiersc.resources.networkResponse.StatusCode.IM_A_TEAPOT_418
import com.javiersc.resources.networkResponse.StatusCode.IM_USED_226
import com.javiersc.resources.networkResponse.StatusCode.INSUFFICIENT_STORAGE
import com.javiersc.resources.networkResponse.StatusCode.INTERNAL_SERVER_ERROR
import com.javiersc.resources.networkResponse.StatusCode.LENGTH_REQUIRED_411
import com.javiersc.resources.networkResponse.StatusCode.LOCKED_423
import com.javiersc.resources.networkResponse.StatusCode.LOOP_DETECTED
import com.javiersc.resources.networkResponse.StatusCode.METHOD_NOT_ALLOWED_405
import com.javiersc.resources.networkResponse.StatusCode.MISDIRECTED_REQUEST_421
import com.javiersc.resources.networkResponse.StatusCode.MOVED_PERMANENTLY_301
import com.javiersc.resources.networkResponse.StatusCode.MULTIPLE_CHOICE_300
import com.javiersc.resources.networkResponse.StatusCode.MULTI_STATUS_207
import com.javiersc.resources.networkResponse.StatusCode.NETWORK_AUTHENTICATION_REQUIRED
import com.javiersc.resources.networkResponse.StatusCode.NON_AUTHORITATIVE_INFORMATION_203
import com.javiersc.resources.networkResponse.StatusCode.NOT_ACCEPTABLE_406
import com.javiersc.resources.networkResponse.StatusCode.NOT_EXTENDED
import com.javiersc.resources.networkResponse.StatusCode.NOT_FOUND_404
import com.javiersc.resources.networkResponse.StatusCode.NOT_IMPLEMENTED
import com.javiersc.resources.networkResponse.StatusCode.NOT_MODIFIED_304
import com.javiersc.resources.networkResponse.StatusCode.NO_CONTENT_204
import com.javiersc.resources.networkResponse.StatusCode.OK_200
import com.javiersc.resources.networkResponse.StatusCode.PARTIAL_CONTENT_206
import com.javiersc.resources.networkResponse.StatusCode.PAYLOAD_TOO_LARGE_413
import com.javiersc.resources.networkResponse.StatusCode.PAYMENT_REQUIRED_402
import com.javiersc.resources.networkResponse.StatusCode.PERMANENT_REDIRECT_308
import com.javiersc.resources.networkResponse.StatusCode.PRECONDITION_FAILED_412
import com.javiersc.resources.networkResponse.StatusCode.PRECONDITION_REQUIRED_428
import com.javiersc.resources.networkResponse.StatusCode.PROCESSING_102
import com.javiersc.resources.networkResponse.StatusCode.PROXY_AUTHENTICATION_REQUIRED_407
import com.javiersc.resources.networkResponse.StatusCode.REQUESTED_RANGE_NOT_SATISFIABLE_416
import com.javiersc.resources.networkResponse.StatusCode.REQUEST_HEADER_FIELDS_TOO_LARGE_431
import com.javiersc.resources.networkResponse.StatusCode.REQUEST_TIMEOUT_408
import com.javiersc.resources.networkResponse.StatusCode.RESET_CONTENT_205
import com.javiersc.resources.networkResponse.StatusCode.SEE_OTHER_303
import com.javiersc.resources.networkResponse.StatusCode.SERVICE_UNAVAILABLE
import com.javiersc.resources.networkResponse.StatusCode.SWITCHING_PROTOCOL_101
import com.javiersc.resources.networkResponse.StatusCode.SWITCH_PROXY_306
import com.javiersc.resources.networkResponse.StatusCode.TEMPORARY_REDIRECT_307
import com.javiersc.resources.networkResponse.StatusCode.TOO_MANY_REQUEST_429
import com.javiersc.resources.networkResponse.StatusCode.UNAUTHORIZED_401
import com.javiersc.resources.networkResponse.StatusCode.UNAVAILABLE_FOR_LEGAL_REASONS_451
import com.javiersc.resources.networkResponse.StatusCode.UNPROCESSABLE_ENTITY_422
import com.javiersc.resources.networkResponse.StatusCode.UNSUPPORTED_MEDIA_TYPE_415
import com.javiersc.resources.networkResponse.StatusCode.UPGRADE_REQUIRED_426
import com.javiersc.resources.networkResponse.StatusCode.URI_TOO_LONG_414
import com.javiersc.resources.networkResponse.StatusCode.USE_PROXY_305
import com.javiersc.resources.networkResponse.StatusCode.VARIANT_ALSO_NEGOTIATES
import com.javiersc.resources.networkResponse.statusCode
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
    when (code.statusCode) {
        CONTINUE_100 -> complete(Continue(headers))
        SWITCHING_PROTOCOL_101 -> complete(SwitchingProtocol(headers))
        PROCESSING_102 -> complete(Processing(headers))
        EARLY_HINTS_103 -> complete(EarlyHints(headers))
        else -> deferred.complete(NetworkResponse.Info.Custom(code, headers))
    }
}

@Suppress("ComplexMethod")
internal fun <R, E> handle2xx(
    deferred: CompletableDeferred<NetworkResponse<R, E>>,
    code: Int,
    body: R?,
    headers: Headers?
) = with(deferred) {
    if (body == null) when (code.statusCode) {
        NO_CONTENT_204 -> complete(NoContent(headers))
        RESET_CONTENT_205 -> complete(ResetContent(headers))
        else -> complete(NoContent(headers))
    } else when (code.statusCode) {
        OK_200 -> complete(OK(body, headers))
        CREATED_201 -> complete(Created(body, headers))
        ACCEPTED_202 -> complete(Accepted(body, headers))
        NON_AUTHORITATIVE_INFORMATION_203 -> complete(NonAuthoritativeInformation(body, headers))
        NO_CONTENT_204 -> complete(NoContent(headers))
        RESET_CONTENT_205 -> complete(ResetContent(headers))
        PARTIAL_CONTENT_206 -> complete(PartialContent(body, headers))
        MULTI_STATUS_207 -> complete(MultiStatus(body, headers))
        ALREADY_REPORTED_208 -> complete(AlreadyReported(body, headers))
        IM_USED_226 -> complete(ImUsed(body, headers))
        else -> complete(Custom(body, code, headers))
    }
}

internal fun <R, E> handle3xx(
    deferred: CompletableDeferred<NetworkResponse<R, E>>,
    code: Int,
    headers: Headers?
) = with(deferred) {
    when (code.statusCode) {
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
    when (code.statusCode) {
        BAD_REQUEST_400 -> complete(BadRequest(errorBody, headers))
        UNAUTHORIZED_401 -> complete(Unauthorized(errorBody, headers))
        PAYMENT_REQUIRED_402 -> complete(PaymentRequired(errorBody, headers))
        FORBIDDEN_403 -> complete(Forbidden(errorBody, headers))
        NOT_FOUND_404 -> complete(NotFound(errorBody, headers))
        METHOD_NOT_ALLOWED_405 -> complete(MethodNotAllowed(errorBody, headers))
        NOT_ACCEPTABLE_406 -> complete(NotAcceptable(errorBody, headers))
        PROXY_AUTHENTICATION_REQUIRED_407 ->
            complete(ProxyAuthenticationRequired(errorBody, headers))
        REQUEST_TIMEOUT_408 -> complete(RequestTimeout(errorBody, headers))
        CONFLICT_409 -> complete(Conflict(errorBody, headers))
        GONE_410 -> complete(Gone(errorBody, headers))
        LENGTH_REQUIRED_411 -> complete(LengthRequired(errorBody, headers))
        PRECONDITION_FAILED_412 -> complete(PreconditionFailed(errorBody, headers))
        PAYLOAD_TOO_LARGE_413 -> complete(PayloadTooLarge(errorBody, headers))
        URI_TOO_LONG_414 -> complete(UriTooLong(errorBody, headers))
        UNSUPPORTED_MEDIA_TYPE_415 -> complete(UnsupportedMediaType(errorBody, headers))
        REQUESTED_RANGE_NOT_SATISFIABLE_416 ->
            complete(RequestedRangeNotSatisfiable(errorBody, headers))
        EXPECTATION_FAILED_417 -> complete(ExpectationFailed(errorBody, headers))
        IM_A_TEAPOT_418 -> complete(ImATeapot(errorBody, headers))
        MISDIRECTED_REQUEST_421 -> complete(MisdirectedRequest(errorBody, headers))
        UNPROCESSABLE_ENTITY_422 -> complete(UnprocessableEntity(errorBody, headers))
        LOCKED_423 -> complete(Locked(errorBody, headers))
        FAILED_DEPENDENCY_424 -> complete(FailedDependency(errorBody, headers))
        UPGRADE_REQUIRED_426 -> complete(UpgradeRequired(errorBody, headers))
        PRECONDITION_REQUIRED_428 -> complete(PreconditionRequired(errorBody, headers))
        TOO_MANY_REQUEST_429 -> complete(TooManyRequest(errorBody, headers))
        REQUEST_HEADER_FIELDS_TOO_LARGE_431 -> complete(RequestHeaderFieldsTooLarge(errorBody, headers))
        UNAVAILABLE_FOR_LEGAL_REASONS_451 -> complete(UnavailableForLegalReasons(errorBody, headers))
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
    when (code.statusCode) {
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
