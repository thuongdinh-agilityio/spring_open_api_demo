package com.agilityio.open_api_demo.dto.response;

import com.agilityio.open_api_demo.common.util.DateUtils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorResponse {
    private Object errors;
    private Object metadata;
    private ResponseStatus status;

    public void addErrorMsgToResponse(String errorMsg, Exception ex) {
        ErrorResponseMessage error = new ErrorResponseMessage()
                .setDetails(errorMsg)
                .setMessage(ex.getMessage())
                .setTimestamp(DateUtils.today());
        setErrors(error);
    }

    public static ErrorResponse unauthorized() {
        ErrorResponse response = new ErrorResponse();
        response.setStatus(ResponseStatus.UNAUTHORIZED);
        return response;
    }

    public static ErrorResponse validationException() {
        ErrorResponse response = new ErrorResponse();
        response.setStatus(ResponseStatus.VALIDATION_EXCEPTION);
        return response;
    }

    public static ErrorResponse wrongCredentials() {
        ErrorResponse response = new ErrorResponse();
        response.setStatus(ResponseStatus.WRONG_CREDENTIALS);
        return response;
    }

    public static ErrorResponse accessDenied() {
        ErrorResponse response = new ErrorResponse();
        response.setStatus(ResponseStatus.ACCESS_DENIED);
        return response;
    }

    public static ErrorResponse exception() {
        ErrorResponse response = new ErrorResponse();
        response.setStatus(ResponseStatus.EXCEPTION);
        return response;
    }

    public static ErrorResponse notFound() {
        ErrorResponse response = new ErrorResponse();
        response.setStatus(ResponseStatus.NOT_FOUND);
        return response;
    }

    public static ErrorResponse duplicateEntity() {
        ErrorResponse response = new ErrorResponse();
        response.setStatus(ResponseStatus.DUPLICATE_ENTITY);
        return response;
    }

}
