package com.alkemy.ong.dto;

import com.alkemy.ong.model.Category;
import com.alkemy.ong.model.Testimonial;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

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

    @JsonIgnoreProperties({"news"})
    private List<Testimonial> testimonials;

}
