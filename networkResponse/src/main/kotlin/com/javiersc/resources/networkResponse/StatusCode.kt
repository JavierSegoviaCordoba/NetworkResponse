package com.javiersc.resources.networkResponse

object StatusCode {
    const val CONTINUE_100 = 100
    const val SWITCHING_PROTOCOL_101 = 101
    const val PROCESSING_102 = 102
    const val EARLY_HINTS_103 = 103

    const val OK_200 = 200
    const val CREATED_201 = 201
    const val ACCEPTED_202 = 202
    const val NON_AUTHORITATIVE_INFORMATION_203 = 203
    const val NO_CONTENT_204 = 204
    const val RESET_CONTENT_205 = 205
    const val PARTIAL_CONTENT_206 = 206
    const val MULTI_STATUS_207 = 207
    const val ALREADY_REPORTED_208 = 208
    const val IM_USED_226 = 226

    const val MULTIPLE_CHOICE_300 = 300
    const val MOVED_PERMANENTLY_301 = 301
    const val FOUND_302 = 302
    const val SEE_OTHER_303 = 303
    const val NOT_MODIFIED_304 = 304
    const val USE_PROXY_305 = 305
    const val SWITCH_PROXY_306 = 306
    const val TEMPORARY_REDIRECT_307 = 307
    const val PERMANENT_REDIRECT_308 = 308

    const val BAD_REQUEST = 400
    const val UNAUTHORIZED = 401
    const val PAYMENT_REQUIRED = 402
    const val FORBIDDEN = 403
    const val NOT_FOUND = 404
    const val METHOD_NOT_ALLOWED = 405
    const val NOT_ACCEPTABLE = 406
    const val PROXY_AUTHENTICATION_REQUIRED = 407
    const val REQUEST_TIMEOUT = 408
    const val CONFLICT = 409
    const val GONE = 410
    const val LENGTH_REQUIRED = 411
    const val PRECONDITION_FAILED = 412
    const val PAYLOAD_TOO_LARGE = 413
    const val URI_TOO_LONG = 414
    const val UNSUPPORTED_MEDIA_TYPE = 415
    const val REQUESTED_RANGE_NOT_SATISFIABLE = 416
    const val EXPECTATION_FAILED = 417
    const val IM_A_TEAPOT = 418
    const val MISDIRECTED_REQUEST = 421
    const val UNPROCESSABLE_ENTITY = 422
    const val LOCKED = 423
    const val FAILED_DEPENDENCY = 424
    const val UPGRADE_REQUIRED = 426
    const val PRECONDITION_REQUIRED = 428
    const val TOO_MANY_REQUEST = 429
    const val REQUEST_HEADER_FIELDS_TOO_LARGE = 431
    const val UNAVAILABLE_FOR_LEGAL_REASONS = 451

    const val INTERNAL_SERVER_ERROR = 500
    const val NOT_IMPLEMENTED = 501
    const val BAD_GATEWAY = 502
    const val SERVICE_UNAVAILABLE = 503
    const val GATEWAY_TIMEOUT = 504
    const val HTTP_VERSION_NOT_SUPPORTED = 505
    const val VARIANT_ALSO_NEGOTIATES = 506
    const val INSUFFICIENT_STORAGE = 507
    const val LOOP_DETECTED = 508
    const val NOT_EXTENDED = 510
    const val NETWORK_AUTHENTICATION_REQUIRED = 511
}
