package com.alkemy.ong.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
@Data
public class ForbiddenUpdate extends RuntimeException{

    private String resourceName;
    private String fieldName;
    private long fieldValue;

    public ForbiddenUpdate(String resourceName, String fieldName, long fieldValue) {
        super(String.format("cannot make modifications to the object %s con : %s : '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
