package com.alkemy.ong.service.mapper;

import com.alkemy.ong.dto.OrganizationDto;
import com.alkemy.ong.dto.OrganizationUpdateDTO;
import com.alkemy.ong.model.Organization;
import org.springframework.stereotype.Component;

@Component
public class OrganizationMapper {

    public OrganizationDto OrganizationEntityToDTO (Organization organization){
        OrganizationDto organizationDTO = OrganizationDto
                .builder()
                .name(organization.getEmail())
                .image(organization.getImage())
                .address(organization.getAddress())
                .phone(organization.getPhone())
                .build();

        return organizationDTO;
    }

    public OrganizationUpdateDTO organizationEntityToOrganizationUpdateDTO (Organization organization){
        return OrganizationUpdateDTO.builder()
                .name(organization.getName())
                .phone(organization.getPhone())
                .address(organization.getAddress())
                .image(organization.getImage())
                .aboutUsText(organization.getAboutUsText())
                .email(organization.getEmail())
                .welcomeText(organization.getWelcomeText())
                .build();
    }
}