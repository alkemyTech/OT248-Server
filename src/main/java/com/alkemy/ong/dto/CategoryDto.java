package com.alkemy.ong.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CategoryDto {

    private Long categoyId;

    @NotNull(message = "Name may not be null")
    @Pattern(regexp = "[a-bA-B0-9]", message = "This field only accepts letters and numbers.")
    private String name;

    private String description;

    private String image;
}
