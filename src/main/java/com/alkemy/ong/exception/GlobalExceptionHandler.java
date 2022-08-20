package com.alkemy.ong.exception;

import java.io.IOException;
import java.time.LocalDateTime;

import com.amazonaws.services.kms.model.AlreadyExistsException;
import com.amazonaws.services.workdocs.model.EntityAlreadyExistsException;
import javassist.NotFoundException;
import org.hibernate.TypeMismatchException;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Objects;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

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

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseBody
    public MessageResponse handleNotFound (Exception e, HttpServletRequest request){
        return new MessageResponse (LocalDateTime.now(), e, request);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler({NameAlreadyExists.class,
            BadCredentialsException.class})
    @ResponseBody
    public MessageResponse handleAlreadyExists (Exception e, HttpServletRequest request) {
        return new MessageResponse(LocalDateTime.now(), e, request);
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public MessageResponse handleResourceNotFound (Exception e, HttpServletRequest request){
        return new MessageResponse(LocalDateTime.now(), e, request);
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(IOException.class)
    @ResponseBody
    public MessageResponse mailNotFound (Exception e, HttpServletRequest request){
        return new MessageResponse (LocalDateTime.now(), e, request);

    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<MessageResponse> handlerNotFound(
        ResourceNotFoundException resourceNotFoundException,
        HttpServletRequest httpServletRequest
    ){
        return new ResponseEntity<>(new MessageResponse(
            LocalDateTime.now(),
            resourceNotFoundException,
            httpServletRequest
        ), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(ForbiddenUpdate.class)
    public ResponseEntity<MessageResponse> handleForbiddenUpdate(
            ForbiddenUpdate forbiddenUpdate,
            HttpServletRequest httpServletRequest
    ){
        return new ResponseEntity<>(new MessageResponse(
                LocalDateTime.now(),
                forbiddenUpdate,
                httpServletRequest
        ), HttpStatus.FORBIDDEN);
    }



}

    


