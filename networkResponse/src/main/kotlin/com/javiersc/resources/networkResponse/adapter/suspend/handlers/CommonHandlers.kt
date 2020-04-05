package com.javiersc.resources.networkResponse.adapter.suspend.handlers

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
import com.javiersc.resources.networkResponse.StatusCode.BAD_GATEWAY_502
import com.javiersc.resources.networkResponse.StatusCode.BAD_REQUEST_400
import com.javiersc.resources.networkResponse.StatusCode.CONFLICT_409
import com.javiersc.resources.networkResponse.StatusCode.CONTINUE_100
import com.javiersc.resources.networkResponse.StatusCode.CREATED_201
import com.javiersc.resources.networkResponse.StatusCode.EARLY_HINTS_103
import com.javiersc.resources.networkResponse.StatusCode.EXPECTATION_FAILED_417
import com.javiersc.resources.networkResponse.StatusCode.FAILED_DEPENDENCY_424
import com.javiersc.resources.networkResponse.StatusCode.FORBIDDEN_403
import com.javiersc.resources.networkResponse.StatusCode.FOUND_302
import com.javiersc.resources.networkResponse.StatusCode.GATEWAY_TIMEOUT_504
import com.javiersc.resources.networkResponse.StatusCode.GONE_410
import com.javiersc.resources.networkResponse.StatusCode.HTTP_VERSION_NOT_SUPPORTED_505
import com.javiersc.resources.networkResponse.StatusCode.IM_A_TEAPOT_418
import com.javiersc.resources.networkResponse.StatusCode.IM_USED_226
import com.javiersc.resources.networkResponse.StatusCode.INSUFFICIENT_STORAGE_507
import com.javiersc.resources.networkResponse.StatusCode.INTERNAL_SERVER_ERROR_500
import com.javiersc.resources.networkResponse.StatusCode.LENGTH_REQUIRED_411
import com.javiersc.resources.networkResponse.StatusCode.LOCKED_423
import com.javiersc.resources.networkResponse.StatusCode.LOOP_DETECTED_508
import com.javiersc.resources.networkResponse.StatusCode.METHOD_NOT_ALLOWED_405
import com.javiersc.resources.networkResponse.StatusCode.MISDIRECTED_REQUEST_421
import com.javiersc.resources.networkResponse.StatusCode.MOVED_PERMANENTLY_301
import com.javiersc.resources.networkResponse.StatusCode.MULTIPLE_CHOICE_300
import com.javiersc.resources.networkResponse.StatusCode.MULTI_STATUS_207
import com.javiersc.resources.networkResponse.StatusCode.NETWORK_AUTHENTICATION_REQUIRED_511
import com.javiersc.resources.networkResponse.StatusCode.NON_AUTHORITATIVE_INFORMATION_203
import com.javiersc.resources.networkResponse.StatusCode.NOT_ACCEPTABLE_406
import com.javiersc.resources.networkResponse.StatusCode.NOT_EXTENDED_510
import com.javiersc.resources.networkResponse.StatusCode.NOT_FOUND_404
import com.javiersc.resources.networkResponse.StatusCode.NOT_IMPLEMENTED_501
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
import com.javiersc.resources.networkResponse.StatusCode.SERVICE_UNAVAILABLE_503
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
import com.javiersc.resources.networkResponse.StatusCode.VARIANT_ALSO_NEGOTIATES_506
import com.javiersc.resources.networkResponse.adapter.suspend.NetworkResponseSuspendCall
import com.javiersc.resources.networkResponse.statusCode
import okhttp3.Headers
import retrofit2.Callback
import retrofit2.Response

internal fun <R : Any, E : Any> handleAllNoSuccess(
    call: NetworkResponseSuspendCall<R, E>,
    callback: Callback<NetworkResponse<R, E>>,
    code: Int,
    errorBody: E?,
    headers: Headers
) {
    @Suppress("MagicNumber")
    when (code) {
        in 100..199 -> handle1xx(call, callback, code, headers)
        in 300..399 -> handle3xx(call, callback, code, headers)
        in 400..499 -> handle4xx(call, callback, code, errorBody, headers)
        in 500..599 -> handle5xx(call, callback, code, errorBody, headers)
    }
}

internal fun <R : Any, E : Any> handle1xx(
    call: NetworkResponseSuspendCall<R, E>,
    callback: Callback<NetworkResponse<R, E>>,
    code: Int,
    headers: Headers
) = with(callback) {
    when (code.statusCode) {
        CONTINUE_100 -> onResponse(call, Response.success(Continue(headers)))
        SWITCHING_PROTOCOL_101 -> onResponse(call, Response.success(SwitchingProtocol(headers)))
        PROCESSING_102 -> onResponse(call, Response.success(Processing(headers)))
        EARLY_HINTS_103 -> onResponse(call, Response.success(EarlyHints(headers)))
        else -> onResponse(call, Response.success(NetworkResponse.Info.Custom(code, headers)))
    }
}

@Suppress("ComplexMethod")
internal fun <R : Any, E : Any> handle2xx(
    call: NetworkResponseSuspendCall<R, E>,
    callback: Callback<NetworkResponse<R, E>>,
    code: Int,
    body: R?,
    headers: Headers
) = with(callback) {
    if (body == null) when (code.statusCode) {
        NO_CONTENT_204 -> onResponse(call, Response.success(NoContent(headers)))
        RESET_CONTENT_205 -> onResponse(call, Response.success(ResetContent(headers)))
        else -> onResponse(call, Response.success(NoContent(headers)))
    } else when (code.statusCode) {
        OK_200 -> onResponse(call, Response.success(OK(body, headers)))
        CREATED_201 -> onResponse(call, Response.success(Created(body, headers)))
        ACCEPTED_202 -> onResponse(call, Response.success(Accepted(body, headers)))
        NON_AUTHORITATIVE_INFORMATION_203 ->
            onResponse(call, Response.success(NonAuthoritativeInformation(body, headers)))
        NO_CONTENT_204 -> onResponse(call, Response.success(NoContent(headers)))
        RESET_CONTENT_205 -> onResponse(call, Response.success(ResetContent(headers)))
        PARTIAL_CONTENT_206 -> onResponse(call, Response.success(PartialContent(body, headers)))
        MULTI_STATUS_207 -> onResponse(call, Response.success(MultiStatus(body, headers)))
        ALREADY_REPORTED_208 -> onResponse(call, Response.success(AlreadyReported(body, headers)))
        IM_USED_226 -> onResponse(call, Response.success(ImUsed(body, headers)))
        else -> onResponse(call, Response.success(Custom(body, code, headers)))
    }
}

internal fun <R : Any, E : Any> handle3xx(
    call: NetworkResponseSuspendCall<R, E>,
    callback: Callback<NetworkResponse<R, E>>,
    code: Int,
    headers: Headers
) = with(callback) {
    when (code.statusCode) {
        MULTIPLE_CHOICE_300 -> onResponse(call, Response.success(MultipleChoices(headers)))
        MOVED_PERMANENTLY_301 -> onResponse(call, Response.success(MovedPermanently(headers)))
        FOUND_302 -> onResponse(call, Response.success(Found(headers)))
        SEE_OTHER_303 -> onResponse(call, Response.success(SeeOther(headers)))
        NOT_MODIFIED_304 -> onResponse(call, Response.success(NotModified(headers)))
        USE_PROXY_305 -> onResponse(call, Response.success(UseProxy(headers)))
        SWITCH_PROXY_306 -> onResponse(call, Response.success(SwitchProxy(headers)))
        TEMPORARY_REDIRECT_307 -> onResponse(call, Response.success(TemporaryRedirect(headers)))
        PERMANENT_REDIRECT_308 -> onResponse(call, Response.success(PermanentRedirect(headers)))
        else ->
            onResponse(call, Response.success(NetworkResponse.Redirection.Custom(code, headers)))
    }
}

@Suppress("ComplexMethod")
internal fun <R : Any, E : Any> handle4xx(
    call: NetworkResponseSuspendCall<R, E>,
    callback: Callback<NetworkResponse<R, E>>,
    code: Int,
    errorBody: E?,
    headers: Headers
) = with(callback) {
    when (code.statusCode) {
        BAD_REQUEST_400 -> onResponse(call, Response.success(BadRequest(errorBody, headers)))
        UNAUTHORIZED_401 -> onResponse(call, Response.success(Unauthorized(errorBody, headers)))
        PAYMENT_REQUIRED_402 ->
            onResponse(call, Response.success(PaymentRequired(errorBody, headers)))
        FORBIDDEN_403 -> onResponse(call, Response.success(Forbidden(errorBody, headers)))
        NOT_FOUND_404 -> onResponse(call, Response.success(NotFound(errorBody, headers)))
        METHOD_NOT_ALLOWED_405 ->
            onResponse(call, Response.success(MethodNotAllowed(errorBody, headers)))
        NOT_ACCEPTABLE_406 -> onResponse(call, Response.success(NotAcceptable(errorBody, headers)))
        PROXY_AUTHENTICATION_REQUIRED_407 ->
            onResponse(call, Response.success(ProxyAuthenticationRequired(errorBody, headers)))
        REQUEST_TIMEOUT_408 ->
            onResponse(call, Response.success(RequestTimeout(errorBody, headers)))
        CONFLICT_409 -> onResponse(call, Response.success(Conflict(errorBody, headers)))
        GONE_410 -> onResponse(call, Response.success(Gone(errorBody, headers)))
        LENGTH_REQUIRED_411 ->
            onResponse(call, Response.success(LengthRequired(errorBody, headers)))
        PRECONDITION_FAILED_412 ->
            onResponse(call, Response.success(PreconditionFailed(errorBody, headers)))
        PAYLOAD_TOO_LARGE_413 -> onResponse(call, Response.success(PayloadTooLarge(errorBody, headers)))
        URI_TOO_LONG_414 -> onResponse(call, Response.success(UriTooLong(errorBody, headers)))
        UNSUPPORTED_MEDIA_TYPE_415 ->
            onResponse(call, Response.success(UnsupportedMediaType(errorBody, headers)))
        REQUESTED_RANGE_NOT_SATISFIABLE_416 ->
            onResponse(call, Response.success(RequestedRangeNotSatisfiable(errorBody, headers)))
        EXPECTATION_FAILED_417 ->
            onResponse(call, Response.success(ExpectationFailed(errorBody, headers)))
        IM_A_TEAPOT_418 -> onResponse(call, Response.success(ImATeapot(errorBody, headers)))
        MISDIRECTED_REQUEST_421 ->
            onResponse(call, Response.success(MisdirectedRequest(errorBody, headers)))
        UNPROCESSABLE_ENTITY_422 ->
            onResponse(call, Response.success(UnprocessableEntity(errorBody, headers)))
        LOCKED_423 -> onResponse(call, Response.success(Locked(errorBody, headers)))
        FAILED_DEPENDENCY_424 ->
            onResponse(call, Response.success(FailedDependency(errorBody, headers)))
        UPGRADE_REQUIRED_426 -> onResponse(call, Response.success(UpgradeRequired(errorBody, headers)))
        PRECONDITION_REQUIRED_428 ->
            onResponse(call, Response.success(PreconditionRequired(errorBody, headers)))
        TOO_MANY_REQUEST_429 -> onResponse(call, Response.success(TooManyRequest(errorBody, headers)))
        REQUEST_HEADER_FIELDS_TOO_LARGE_431 ->
            onResponse(call, Response.success(RequestHeaderFieldsTooLarge(errorBody, headers)))
        UNAVAILABLE_FOR_LEGAL_REASONS_451 ->
            onResponse(call, Response.success(UnavailableForLegalReasons(errorBody, headers)))
        else -> onResponse(
            call, Response.success(NetworkResponse.ClientError.Custom(errorBody, code, headers))
        )
    }
}

@Suppress("ComplexMethod")
internal fun <R : Any, E : Any> handle5xx(
    call: NetworkResponseSuspendCall<R, E>,
    callback: Callback<NetworkResponse<R, E>>,
    code: Int,
    errorBody: E?,
    headers: Headers
) = with(callback) {
    when (code.statusCode) {
        INTERNAL_SERVER_ERROR_500 ->
            onResponse(call, Response.success(InternalServerError(errorBody, headers)))
        NOT_IMPLEMENTED_501 -> onResponse(call, Response.success(NotImplemented(errorBody, headers)))
        BAD_GATEWAY_502 -> onResponse(call, Response.success(BadGateway(errorBody, headers)))
        SERVICE_UNAVAILABLE_503 ->
            onResponse(call, Response.success(ServiceUnavailable(errorBody, headers)))
        GATEWAY_TIMEOUT_504 -> onResponse(call, Response.success(GatewayTimeout(errorBody, headers)))
        HTTP_VERSION_NOT_SUPPORTED_505 ->
            onResponse(call, Response.success(HttpVersionNotSupported(errorBody, headers)))
        VARIANT_ALSO_NEGOTIATES_506 ->
            onResponse(call, Response.success(VariantAlsoNegotiates(errorBody, headers)))
        INSUFFICIENT_STORAGE_507 ->
            onResponse(call, Response.success(InsufficientStorage(errorBody, headers)))
        LOOP_DETECTED_508 -> onResponse(call, Response.success(LoopDetected(errorBody, headers)))
        NOT_EXTENDED_510 -> onResponse(call, Response.success(NotExtended(errorBody, headers)))
        NETWORK_AUTHENTICATION_REQUIRED_511 ->
            onResponse(call, Response.success(NetworkAuthenticationRequired(errorBody, headers)))
        else -> onResponse(
            call, Response.success(NetworkResponse.ServerError.Custom(errorBody, code, headers))
        )
    }
}
