package com.fitge.api.exception;

import java.util.Optional;

import org.springframework.http.HttpStatus;


public class ApiException extends RuntimeException {
    private final String code;
    private final HttpStatus status;
    private final Optional<Throwable> cause;

    public ApiException(String code, HttpStatus status, Optional<Throwable> cause) {
        super(code, cause.orElse(null));
        this.code = code;
        this.status = status;
        this.cause = cause;
    }

    public ApiException(String code, HttpStatus status) {
        this(code, status, Optional.empty());
    }

    public ApiException(String code, HttpStatus status, Throwable cause) {
        this(code, status, Optional.ofNullable(cause));
    }

    public String getCode() {
        return code;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public Optional<Throwable> getCauseOptional() {
        return cause;
    }
}