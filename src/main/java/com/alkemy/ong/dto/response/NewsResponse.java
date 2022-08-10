package com.alkemy.ong.dto.response;

import com.alkemy.ong.model.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter @Setter
@Builder
public class NewsResponse {

    private Long id;
    private String name;
    private String content;
    private String image;
    private Long categoryId;
    private Date createDate;

}
