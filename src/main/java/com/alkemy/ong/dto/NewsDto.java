package com.alkemy.ong.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NewsDto {


    private Long id;

    @NotBlank(message = "Name can not be empty.")
    private String name;

    @NotBlank(message = "Content can not be empty.")
    private String content;

    private String image;

    private Category categoryId;

    private Date createDate;

    private Date updateDate;

}
