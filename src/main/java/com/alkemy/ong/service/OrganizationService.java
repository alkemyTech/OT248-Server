package com.alkemy.ong.service;
import com.alkemy.ong.dto.OrganizationDto;
import com.alkemy.ong.dto.OrganizationUpdateDTO;

public interface OrganizationService {

    public OrganizationDto getOrganizationPublic();

    OrganizationUpdateDTO updateOrganization(OrganizationUpdateDTO organizationUpdateDTO);

}

