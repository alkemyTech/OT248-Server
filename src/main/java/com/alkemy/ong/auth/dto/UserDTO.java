package com.alkemy.ong.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @NotBlank(message = "Email can not be empty.")
    @Email
    private String email;
    @NotEmpty(message = "Password can not be empty")
    @Size(min = 4, max = 20,
            message = "Password must be greater than 4 characters and less than 20")
    private String password;

    @NotBlank(message = "Firstname can not be empty.")
    private String firstName;

    @NotBlank(message = "Lastname can not be empty.")
    private String lastName;

    private String photo;

}
