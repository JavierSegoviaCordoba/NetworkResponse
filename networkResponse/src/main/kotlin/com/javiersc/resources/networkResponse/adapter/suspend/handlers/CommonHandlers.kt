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
import com.javiersc.resources.networkResponse.StatusCode.BAD_GATEWAY
import com.javiersc.resources.networkResponse.StatusCode.BAD_REQUEST
import com.javiersc.resources.networkResponse.StatusCode.CONFLICT
import com.javiersc.resources.networkResponse.StatusCode.CONTINUE_100
import com.javiersc.resources.networkResponse.StatusCode.CREATED_201
import com.javiersc.resources.networkResponse.StatusCode.EARLY_HINTS_103
import com.javiersc.resources.networkResponse.StatusCode.EXPECTATION_FAILED
import com.javiersc.resources.networkResponse.StatusCode.FAILED_DEPENDENCY
import com.javiersc.resources.networkResponse.StatusCode.FORBIDDEN
import com.javiersc.resources.networkResponse.StatusCode.FOUND_302
import com.javiersc.resources.networkResponse.StatusCode.GATEWAY_TIMEOUT
import com.javiersc.resources.networkResponse.StatusCode.GONE
import com.javiersc.resources.networkResponse.StatusCode.HTTP_VERSION_NOT_SUPPORTED
import com.javiersc.resources.networkResponse.StatusCode.IM_A_TEAPOT
import com.javiersc.resources.networkResponse.StatusCode.IM_USED_226
import com.javiersc.resources.networkResponse.StatusCode.INSUFFICIENT_STORAGE
import com.javiersc.resources.networkResponse.StatusCode.INTERNAL_SERVER_ERROR
import com.javiersc.resources.networkResponse.StatusCode.LENGTH_REQUIRED
import com.javiersc.resources.networkResponse.StatusCode.LOCKED
import com.javiersc.resources.networkResponse.StatusCode.LOOP_DETECTED
import com.javiersc.resources.networkResponse.StatusCode.METHOD_NOT_ALLOWED
import com.javiersc.resources.networkResponse.StatusCode.MISDIRECTED_REQUEST
import com.javiersc.resources.networkResponse.StatusCode.MOVED_PERMANENTLY_301
import com.javiersc.resources.networkResponse.StatusCode.MULTIPLE_CHOICE_300
import com.javiersc.resources.networkResponse.StatusCode.MULTI_STATUS_207
import com.javiersc.resources.networkResponse.StatusCode.NETWORK_AUTHENTICATION_REQUIRED
import com.javiersc.resources.networkResponse.StatusCode.NON_AUTHORITATIVE_INFORMATION_203
import com.javiersc.resources.networkResponse.StatusCode.NOT_ACCEPTABLE
import com.javiersc.resources.networkResponse.StatusCode.NOT_EXTENDED
import com.javiersc.resources.networkResponse.StatusCode.NOT_FOUND
import com.javiersc.resources.networkResponse.StatusCode.NOT_IMPLEMENTED
import com.javiersc.resources.networkResponse.StatusCode.NOT_MODIFIED_304
import com.javiersc.resources.networkResponse.StatusCode.NO_CONTENT_204
import com.javiersc.resources.networkResponse.StatusCode.OK_200
import com.javiersc.resources.networkResponse.StatusCode.PARTIAL_CONTENT_206
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
import com.javiersc.resources.networkResponse.StatusCode.RESET_CONTENT_205
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
import com.javiersc.resources.networkResponse.adapter.suspend.NetworkResponseSuspendCall
import okhttp3.Headers
import retrofit2.Callback
import retrofit2.Response

internal fun <R : Any, E : Any> handleAllNoSuccess(
    call: NetworkResponseSuspendCall<R, E>,
    callback: Callback<NetworkResponse<R, E>>,
    code: Int,
    errorBody: E?,
    headers: Headers?
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
    headers: Headers?
) = with(callback) {
    when (code) {
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
    headers: Headers?
) = with(callback) {
    if (body == null) when (code) {
        NO_CONTENT_204 -> onResponse(call, Response.success(NoContent(headers)))
        RESET_CONTENT_205 -> onResponse(call, Response.success(ResetContent(headers)))
        else -> onResponse(call, Response.success(NoContent(headers)))
    } else when (code) {
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
    headers: Headers?
) = with(callback) {
    when (code) {
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
    headers: Headers?
) = with(callback) {
    when (code) {
        BAD_REQUEST -> onResponse(call, Response.success(BadRequest(errorBody, headers)))
        UNAUTHORIZED -> onResponse(call, Response.success(Unauthorized(errorBody, headers)))
        PAYMENT_REQUIRED -> onResponse(call, Response.success(PaymentRequired(errorBody, headers)))
        FORBIDDEN -> onResponse(call, Response.success(Forbidden(errorBody, headers)))
        NOT_FOUND -> onResponse(call, Response.success(NotFound(errorBody, headers)))
        METHOD_NOT_ALLOWED ->
            onResponse(call, Response.success(MethodNotAllowed(errorBody, headers)))
        NOT_ACCEPTABLE -> onResponse(call, Response.success(NotAcceptable(errorBody, headers)))
        PROXY_AUTHENTICATION_REQUIRED ->
            onResponse(call, Response.success(ProxyAuthenticationRequired(errorBody, headers)))
        REQUEST_TIMEOUT -> onResponse(call, Response.success(RequestTimeout(errorBody, headers)))
        CONFLICT -> onResponse(call, Response.success(Conflict(errorBody, headers)))
        GONE -> onResponse(call, Response.success(Gone(errorBody, headers)))
        LENGTH_REQUIRED -> onResponse(call, Response.success(LengthRequired(errorBody, headers)))
        PRECONDITION_FAILED ->
            onResponse(call, Response.success(PreconditionFailed(errorBody, headers)))
        PAYLOAD_TOO_LARGE -> onResponse(call, Response.success(PayloadTooLarge(errorBody, headers)))
        URI_TOO_LONG -> onResponse(call, Response.success(UriTooLong(errorBody, headers)))
        UNSUPPORTED_MEDIA_TYPE ->
            onResponse(call, Response.success(UnsupportedMediaType(errorBody, headers)))
        REQUESTED_RANGE_NOT_SATISFIABLE ->
            onResponse(call, Response.success(RequestedRangeNotSatisfiable(errorBody, headers)))
        EXPECTATION_FAILED ->
            onResponse(call, Response.success(ExpectationFailed(errorBody, headers)))
        IM_A_TEAPOT -> onResponse(call, Response.success(ImATeapot(errorBody, headers)))
        MISDIRECTED_REQUEST ->
            onResponse(call, Response.success(MisdirectedRequest(errorBody, headers)))
        UNPROCESSABLE_ENTITY ->
            onResponse(call, Response.success(UnprocessableEntity(errorBody, headers)))
        LOCKED -> onResponse(call, Response.success(Locked(errorBody, headers)))
        FAILED_DEPENDENCY ->
            onResponse(call, Response.success(FailedDependency(errorBody, headers)))
        UPGRADE_REQUIRED -> onResponse(call, Response.success(UpgradeRequired(errorBody, headers)))
        PRECONDITION_REQUIRED ->
            onResponse(call, Response.success(PreconditionRequired(errorBody, headers)))
        TOO_MANY_REQUEST -> onResponse(call, Response.success(TooManyRequest(errorBody, headers)))
        REQUEST_HEADER_FIELDS_TOO_LARGE ->
            onResponse(call, Response.success(RequestHeaderFieldsTooLarge(errorBody, headers)))
        UNAVAILABLE_FOR_LEGAL_REASONS ->
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
    headers: Headers?
) = with(callback) {
    when (code) {
        INTERNAL_SERVER_ERROR ->
            onResponse(call, Response.success(InternalServerError(errorBody, headers)))
        NOT_IMPLEMENTED -> onResponse(call, Response.success(NotImplemented(errorBody, headers)))
        BAD_GATEWAY -> onResponse(call, Response.success(BadGateway(errorBody, headers)))
        SERVICE_UNAVAILABLE ->
            onResponse(call, Response.success(ServiceUnavailable(errorBody, headers)))
        GATEWAY_TIMEOUT -> onResponse(call, Response.success(GatewayTimeout(errorBody, headers)))
        HTTP_VERSION_NOT_SUPPORTED ->
            onResponse(call, Response.success(HttpVersionNotSupported(errorBody, headers)))
        VARIANT_ALSO_NEGOTIATES ->
            onResponse(call, Response.success(VariantAlsoNegotiates(errorBody, headers)))
        INSUFFICIENT_STORAGE ->
            onResponse(call, Response.success(InsufficientStorage(errorBody, headers)))
        LOOP_DETECTED -> onResponse(call, Response.success(LoopDetected(errorBody, headers)))
        NOT_EXTENDED -> onResponse(call, Response.success(NotExtended(errorBody, headers)))
        NETWORK_AUTHENTICATION_REQUIRED ->
            onResponse(call, Response.success(NetworkAuthenticationRequired(errorBody, headers)))
        else -> onResponse(
            call, Response.success(NetworkResponse.ServerError.Custom(errorBody, code, headers))
        )
    }
}
