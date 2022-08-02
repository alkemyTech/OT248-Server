package com.alkemy.ong.dto;
import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrganizationDto {

    @NotNull(message = "Name can not be empty.")
    private String name;

    @NotNull(message = "Image can not be empty.")
    private String image;

    @NotNull(message = "Address can not be empty.")
    private String address;

    @NotNull(message = "Phone can not be empty.")
    private Integer phone;

    private String urlFacebook;

    private String urlLinkedin;

    private String urlInstagram;

}
