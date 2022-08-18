package com.alkemy.ong.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrganizationResponseDTO {
    private Long id;
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
    private List<SlideResponseDTO> slides;
}
