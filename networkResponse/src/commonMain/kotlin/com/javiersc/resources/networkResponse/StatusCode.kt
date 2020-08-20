package com.javiersc.resources.networkResponse

public object StatusCode {
    public const val CONTINUE_100: Int = 100
    public const val SWITCHING_PROTOCOL_101: Int = 101
    public const val PROCESSING_102: Int = 102
    public const val EARLY_HINTS_103: Int = 103

    public const val OK_200: Int = 200
    public const val CREATED_201: Int = 201
    public const val ACCEPTED_202: Int = 202
    public const val NON_AUTHORITATIVE_INFORMATION_203: Int = 203
    public const val NO_CONTENT_204: Int = 204
    public const val RESET_CONTENT_205: Int = 205
    public const val PARTIAL_CONTENT_206: Int = 206
    public const val MULTI_STATUS_207: Int = 207
    public const val ALREADY_REPORTED_208: Int = 208
    public const val IM_USED_226: Int = 226

    public const val MULTIPLE_CHOICE_300: Int = 300
    public const val MOVED_PERMANENTLY_301: Int = 301
    public const val FOUND_302: Int = 302
    public const val SEE_OTHER_303: Int = 303
    public const val NOT_MODIFIED_304: Int = 304
    public const val USE_PROXY_305: Int = 305
    public const val SWITCH_PROXY_306: Int = 306
    public const val TEMPORARY_REDIRECT_307: Int = 307
    public const val PERMANENT_REDIRECT_308: Int = 308

    public const val BAD_REQUEST_400: Int = 400
    public const val UNAUTHORIZED_401: Int = 401
    public const val PAYMENT_REQUIRED_402: Int = 402
    public const val FORBIDDEN_403: Int = 403
    public const val NOT_FOUND_404: Int = 404
    public const val METHOD_NOT_ALLOWED_405: Int = 405
    public const val NOT_ACCEPTABLE_406: Int = 406
    public const val PROXY_AUTHENTICATION_REQUIRED_407: Int = 407
    public const val REQUEST_TIMEOUT_408: Int = 408
    public const val CONFLICT_409: Int = 409
    public const val GONE_410: Int = 410
    public const val LENGTH_REQUIRED_411: Int = 411
    public const val PRECONDITION_FAILED_412: Int = 412
    public const val PAYLOAD_TOO_LARGE_413: Int = 413
    public const val URI_TOO_LONG_414: Int = 414
    public const val UNSUPPORTED_MEDIA_TYPE_415: Int = 415
    public const val REQUESTED_RANGE_NOT_SATISFIABLE_416: Int = 416
    public const val EXPECTATION_FAILED_417: Int = 417
    public const val IM_A_TEAPOT_418: Int = 418
    public const val MISDIRECTED_REQUEST_421: Int = 421
    public const val UNPROCESSABLE_ENTITY_422: Int = 422
    public const val LOCKED_423: Int = 423
    public const val FAILED_DEPENDENCY_424: Int = 424
    public const val UPGRADE_REQUIRED_426: Int = 426
    public const val PRECONDITION_REQUIRED_428: Int = 428
    public const val TOO_MANY_REQUEST_429: Int = 429
    public const val REQUEST_HEADER_FIELDS_TOO_LARGE_431: Int = 431
    public const val UNAVAILABLE_FOR_LEGAL_REASONS_451: Int = 451

    public const val INTERNAL_SERVER_ERROR_500: Int = 500
    public const val NOT_IMPLEMENTED_501: Int = 501
    public const val BAD_GATEWAY_502: Int = 502
    public const val SERVICE_UNAVAILABLE_503: Int = 503
    public const val GATEWAY_TIMEOUT_504: Int = 504
    public const val HTTP_VERSION_NOT_SUPPORTED_505: Int = 505
    public const val VARIANT_ALSO_NEGOTIATES_506: Int = 506
    public const val INSUFFICIENT_STORAGE_507: Int = 507
    public const val LOOP_DETECTED_508: Int = 508
    public const val NOT_EXTENDED_510: Int = 510
    public const val NETWORK_AUTHENTICATION_REQUIRED_511: Int = 511
}
