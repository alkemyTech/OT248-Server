package com.alkemy.ong.service;
import com.alkemy.ong.dto.OrganizationDto;
import com.alkemy.ong.dto.OrganizationUpdateDTO;
import com.alkemy.ong.dto.SlidesDto;
import com.alkemy.ong.dto.response.OrganizationResponseDTO;
import com.alkemy.ong.dto.response.SlideResponseDTO;

import java.util.List;

public interface OrganizationService {

    OrganizationDto getOrganizationPublic();

    OrganizationUpdateDTO updateOrganization(OrganizationUpdateDTO organizationUpdateDTO);

    OrganizationResponseDTO getOrganizationResponseDTO(List<SlideResponseDTO> slidesDtos, OrganizationDto organizationResponse);
}

