package com.alkemy.ong.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TestimonialDto {

    @NotNull(message = "Name is required.")
    @NotEmpty(message = "Name is required.")
    private String name;

    private String image;
    @NotNull(message = "content can not be null.")
    @NotEmpty(message = "content can not be empty.")
    private String content;
}
