package com.alkemy.ong.dto;

import com.alkemy.ong.model.Organization;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SlidesDto {


    private Long id;

    private String imageUrl;

    private String text;

    private Integer order;

    private Organization organizationId;
}
