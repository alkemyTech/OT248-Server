package com.alkemy.ong.dto;


import com.alkemy.ong.model.Organization;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SlidesDto {

    private String imageUrl;

    private String text;

    private Integer order;

    

}
