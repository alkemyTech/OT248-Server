
package com.alkemy.ong.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactDto {

    private Long idContact;

    @NotNull(message = "Name can not be empty.")
    private String name;

    @NotNull(message = "Phone can not be empty.")
    private Integer phone;

    @NotNull(message = "Email can not be empty.")
    private String email;

    private String message;

    private boolean deleted;
}
