package com.alkemy.ong.service.mapper;

import com.alkemy.ong.dto.SlideRequestDTO;
import com.alkemy.ong.dto.response.SlideResponseDTO;
import com.alkemy.ong.model.Slide;
import com.alkemy.ong.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.Locale;

@Component
public class SlideMapper {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private MessageSource messageSource;

    public SlideResponseDTO entityToDTO (Slide slideEntity){
        return SlideResponseDTO.builder()
                .id(slideEntity.getId())
                .text(slideEntity.getText())
                .imageUrl(slideEntity.getImageUrl())
                .position(slideEntity.getPosition())
                .organizationId(slideEntity.getOrganization().getIdOrganization())
                .build();
    }

    public Slide updateSlide (SlideRequestDTO request, Slide entityFromDB){

        Slide slide = Slide.builder()
                .id(entityFromDB.getId())
                .imageUrl(request.getImageUrl())
                .text(request.getText())
                .position(request.getPosition())
                .build();

        if (request.getOrganizationId() == null) slide.setOrganization(entityFromDB.getOrganization());
        else slide.setOrganization(organizationRepository
                .findById(request.getOrganizationId())
                .orElseThrow(() -> new EntityNotFoundException(
                        messageSource.getMessage("organization.not.found", null, Locale.US))));

        return slide;
    }


}
