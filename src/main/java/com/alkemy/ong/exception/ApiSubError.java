package com.alkemy.ong.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**Representa multiples subErrores en una sola llamada**/
abstract class ApiSubError {}

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
class ApiValidationError extends ApiSubError {
    private String object;
    private String field;
    private Object rejectedValue;
    private String message;

   public ApiValidationError(String object, String message) {
        this.object = object;
        this.message = message;
    }
}