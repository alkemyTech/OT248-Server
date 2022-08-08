package com.alkemy.ong.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class CategoryResponseDTO {

    private Long id;

    private String name;

    private String description;

    private String image;

    private Date updateDate;

}
