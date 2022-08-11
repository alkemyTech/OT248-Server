package com.alkemy.ong.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
public class SlideRequestDTO {

    private String imageUrl;
    @NotBlank(message = "{error.text.empty}")
    private String text;
    @NotNull(message = "{error.position.empty}")
    private Integer position;
    private Long organizationId;

}
