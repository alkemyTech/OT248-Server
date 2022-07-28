package com.alkemy.ong.exception;

import org.hibernate.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ResponseStatusException;
import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TypeMismatchException.class)
    public HttpStatus handleTypeMismatchException(TypeMismatchException exception) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid value '${e.value}'", exception);
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public HttpStatus handleWebExchangeBindException(WebExchangeBindException exception) {
        throw new WebExchangeBindException(Objects.requireNonNull(exception.getMethodParameter()), exception.getBindingResult()) {
            String message = "${fieldError?.field} has invalid value '${fieldError?.rejectedValue}'";
        };
    }
}
