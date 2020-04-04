package com.javiersc.resources.networkResponse

enum class StatusCode(val code: Int) {
    CONTINUE_100(code = 100),
    SWITCHING_PROTOCOL_101(code = 101),
    PROCESSING_102(code = 102),
    EARLY_HINTS_103(code = 103),

    OK_200(code = 200),
    CREATED_201(code = 201),
    ACCEPTED_202(code = 202),
    NON_AUTHORITATIVE_INFORMATION_203(code = 203),
    NO_CONTENT_204(code = 204),
    RESET_CONTENT_205(code = 205),
    PARTIAL_CONTENT_206(code = 206),
    MULTI_STATUS_207(code = 207),
    ALREADY_REPORTED_208(code = 208),
    IM_USED_226(code = 226),

    MULTIPLE_CHOICE_300(code = 300),
    MOVED_PERMANENTLY_301(code = 301),
    FOUND_302(code = 302),
    SEE_OTHER_303(code = 303),
    NOT_MODIFIED_304(code = 304),
    USE_PROXY_305(code = 305),
    SWITCH_PROXY_306(code = 306),
    TEMPORARY_REDIRECT_307(code = 307),
    PERMANENT_REDIRECT_308(code = 308),

    BAD_REQUEST_400(code = 400),
    UNAUTHORIZED_401(code = 401),
    PAYMENT_REQUIRED_402(code = 402),
    FORBIDDEN_403(code = 403),
    NOT_FOUND_404(code = 404),
    METHOD_NOT_ALLOWED_405(code = 405),
    NOT_ACCEPTABLE_406(code = 406),
    PROXY_AUTHENTICATION_REQUIRED_407(code = 407),
    REQUEST_TIMEOUT_408(code = 408),
    CONFLICT_409(code = 409),
    GONE_410(code = 410),
    LENGTH_REQUIRED_411(code = 411),
    PRECONDITION_FAILED(code = 412),
    PAYLOAD_TOO_LARGE(code = 413),
    URI_TOO_LONG(code = 414),
    UNSUPPORTED_MEDIA_TYPE(code = 415),
    REQUESTED_RANGE_NOT_SATISFIABLE(code = 416),
    EXPECTATION_FAILED(code = 417),
    IM_A_TEAPOT(code = 418),
    MISDIRECTED_REQUEST(code = 421),
    UNPROCESSABLE_ENTITY(code = 422),
    LOCKED(code = 423),
    FAILED_DEPENDENCY(code = 424),
    UPGRADE_REQUIRED(code = 426),
    PRECONDITION_REQUIRED(code = 428),
    TOO_MANY_REQUEST(code = 429),
    REQUEST_HEADER_FIELDS_TOO_LARGE(code = 431),
    UNAVAILABLE_FOR_LEGAL_REASONS(code = 451),

    INTERNAL_SERVER_ERROR(code = 500),
    NOT_IMPLEMENTED(code = 501),
    BAD_GATEWAY(code = 502),
    SERVICE_UNAVAILABLE(code = 503),
    GATEWAY_TIMEOUT(code = 504),
    HTTP_VERSION_NOT_SUPPORTED(code = 505),
    VARIANT_ALSO_NEGOTIATES(code = 506),
    INSUFFICIENT_STORAGE(code = 507),
    LOOP_DETECTED(code = 508),
    NOT_EXTENDED(code = 510),
    NETWORK_AUTHENTICATION_REQUIRED(code = 511),
}

internal val Int.statusCode: StatusCode? get() = StatusCode.values().find { it.code == this }
