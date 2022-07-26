package com.alkemy.ong.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ApiError extends RuntimeException {

    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private String debugMessage;
    private List<ApiSubError> subErrors;

    private ApiError() {
        timestamp = LocalDateTime.now();
    }

    public ApiError(HttpStatus status) {
        this();
        this.status = status;
    }

     public ApiError(HttpStatus status, Throwable throwable) {
        this();
        this.status = status;
        this.message = "Unexpected error";
        this.debugMessage = throwable.getLocalizedMessage();
    }

   public ApiError(HttpStatus status, String message, Throwable throwable) {
        this();
        this.status = status;
        this.message = message;
        this.debugMessage = throwable.getLocalizedMessage();
    }
}