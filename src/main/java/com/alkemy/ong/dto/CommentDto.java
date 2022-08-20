package com.alkemy.ong.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

    @NotBlank(message = "body can't be empty")
    @NotNull(message = "body can't be null")
    private String body;

    @Min(1)
    private Long newsId;

}
