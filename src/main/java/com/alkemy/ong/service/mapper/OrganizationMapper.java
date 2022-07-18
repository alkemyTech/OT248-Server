package com.alkemy.ong.service.mapper;

import com.alkemy.ong.dto.OrganizationDto;
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
}