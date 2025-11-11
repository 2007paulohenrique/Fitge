package com.fitge.api.exception;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.fitge.api.exception.exceptionCode.DefaultError;
import com.fitge.api.exception.exceptionCode.SecurityError;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Map<String, Object>> handleApiException(ApiException ex) {
        logger.error("Erro na api", ex.getCause());
        logger.warn("ApiException: code={}, status={}", ex.getCode(), ex.getStatus());

        Map<String, Object> body = new HashMap<>();

        body.put("messageCode", ex.getCode());

        return new ResponseEntity<>(body, ex.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiException> handleValidationException(MethodArgumentNotValidException ex) {
        String messageCode = ex.getBindingResult()
            .getAllErrors()
            .stream()
            .findFirst()
            .map(error -> error.getDefaultMessage())
            .orElse(DefaultError.INVALID_REQUEST);

        throw new ApiException(messageCode, HttpStatus.BAD_REQUEST, ex);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiException> handleConstraintViolationException(ConstraintViolationException ex) {
        String messageCode = ex.getConstraintViolations()
            .stream()
            .findFirst()
            .map(violation -> violation.getMessage())
            .orElse(DefaultError.INVALID_REQUEST);

        throw new ApiException(messageCode, HttpStatus.BAD_REQUEST, ex);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleInternalServerException(Exception ex) {
        logger.error("Erro interno do servidor", ex);

        Map<String, Object> body = new HashMap<>();

        body.put("messageCode", DefaultError.INTERNAL_ERROR);

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiException> handleAccessDeniedException(Exception ex) {
        throw new ApiException(SecurityError.INVALID_USER_ROLE_ERROR, HttpStatus.FORBIDDEN, ex);
    }

}
