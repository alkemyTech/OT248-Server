package com.alkemy.ong.auth.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AuthenticationRequest {

    @NotBlank(message = "Email can not be empty.")
    @Email(message = "Email format incorrect.")
    private String email;
    private String password;

}
