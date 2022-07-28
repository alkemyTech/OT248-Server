package com.alkemy.ong.dto;

import com.alkemy.ong.model.News;
import com.alkemy.ong.model.Users;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TestimonialDto {

    private String name;

    private String image;

    @NotNull(message = "content can´t be null")
    @NotBlank(message = "content can´t be empty")
    private String content;

    @NotNull(message = "new can´t be null")
    @NotBlank(message = "new can´t be empty")
    private News news;

    @NotNull(message = "user can´t be null")
    @NotBlank(message = "user can´t be empty")
    private Users users;
}
