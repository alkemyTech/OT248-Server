package com.alkemy.ong.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Builder
public class ActivityRequestDTO {

    @NotNull(message = "{error.name.notnull}")
    @Pattern(regexp = "[a-zA-Z0-9\\s]*", message = "{error.regex}")
    public String name;
    public String content;
    public String image;

}
