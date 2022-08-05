package com.alkemy.ong.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

    private Long id;

    @NotBlank(message = "body can't be empty")
    @NotNull(message = "body can't be null")
    private String body;

    @NotBlank(message = "userId can't be empty")
    @NotNull(message = "userId can't be null")
    private Long userId;

    @NotBlank(message = "newsId can't be empty")
    @NotNull(message = "newsId can't be null")
    private Long newsId;

}
