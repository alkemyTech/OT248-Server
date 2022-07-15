package com.alkemy.ong.auth.dto;

import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    @NotBlank(message = "Email format incorrect.")
    @Email(message = "Email format incorrect.")
    private String email;

    @Size(min = 8, max = 20,
            message = "Password must be greater than 4 characters and less than 20")
    private String password;

    @NotBlank(message = "Firstname can not be empty.")
    private String firstName;

    @NotBlank(message = "Lastname can not be empty.")
    private String lastName;

    private String photo;

}
