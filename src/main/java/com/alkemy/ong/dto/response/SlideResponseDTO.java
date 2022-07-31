package com.alkemy.ong.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SlideResponseDTO {

    private Long id;
    private String imageUrl;
    private String text;
    private Integer position;
    private Long organizationId;
}
