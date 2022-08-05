package com.alkemy.ong.dto;

import lombok.*;

import javax.validation.constraints.Email;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private String firstName;

    private String lastName;

    @Email
    private String email;

    private String password;

    private String photo;

    private Long roleId;
}
