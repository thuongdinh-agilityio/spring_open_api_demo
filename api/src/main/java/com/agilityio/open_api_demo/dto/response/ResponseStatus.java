package com.agilityio.open_api_demo.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(enumAsRef = true)
enum ResponseStatus {
    OK, BAD_REQUEST, UNAUTHORIZED, VALIDATION_EXCEPTION, EXCEPTION,
    WRONG_CREDENTIALS, ACCESS_DENIED, NOT_FOUND, DUPLICATE_ENTITY
}
