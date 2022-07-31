package com.alkemy.ong.service.mapper;

import com.alkemy.ong.dto.response.SlideResponseDTO;
import com.alkemy.ong.model.Slide;
import org.springframework.stereotype.Component;

@Component
public class SlideMapper {

    public SlideResponseDTO entityToDTO (Slide slideEntity){
        return SlideResponseDTO.builder()
                .id(slideEntity.getId())
                .text(slideEntity.getText())
                .imageUrl(slideEntity.getImageUrl())
                .position(slideEntity.getPosition())
                .organizationId(slideEntity.getOrganization().getIdOrganization())
                .build();
    }

}
