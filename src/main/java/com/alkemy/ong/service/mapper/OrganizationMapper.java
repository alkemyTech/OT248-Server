package com.alkemy.ong.service.mapper;

import com.alkemy.ong.dto.OrganizationDto;
import com.alkemy.ong.dto.OrganizationUpdateDTO;
import com.alkemy.ong.model.Organization;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class OrganizationMapper {

    public OrganizationDto OrganizationEntityToDTO (Organization organization){
        return OrganizationDto
                .builder()
                .name(organization.getEmail())
                .image(organization.getImage())
                .address(organization.getAddress())
                .phone(organization.getPhone())
                .urlFacebook(organization.getUrlFacebook())
                .urlLinkedin(organization.getUrlLinkedin())
                .urlInstagram(organization.getUrlInstagram())
                .build();
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
                .urlFacebook(organization.getUrlFacebook())
                .urlInstagram(organization.getUrlInstagram())
                .urlLinkedin(organization.getUrlLinkedin())
                .build();
    }

    public Organization organizationDTOToEntity (OrganizationUpdateDTO organizationDTO) {

        return Organization.builder()
                .name(organizationDTO.getName())
                .address(organizationDTO.getAddress())
                .phone(organizationDTO.getPhone())
                .email(organizationDTO.getEmail())
                .image(organizationDTO.getImage())
                .aboutUsText(organizationDTO.getAboutUsText())
                .welcomeText(organizationDTO.getWelcomeText())
                .urlFacebook(organizationDTO.getUrlFacebook())
                .urlInstagram(organizationDTO.getUrlInstagram())
                .urlLinkedin(organizationDTO.getUrlLinkedin())
                .updateTimestamp(new Date())
                .build();
    }

    public Organization organizationUpdate (OrganizationUpdateDTO organizationDTO, Organization entity){
        return Organization.builder()
                .idOrganization(entity.getIdOrganization())
                .name(organizationDTO.getName())
                .address(organizationDTO.getAddress())
                .phone(organizationDTO.getPhone())
                .email(organizationDTO.getEmail())
                .image(organizationDTO.getImage())
                .aboutUsText(organizationDTO.getAboutUsText())
                .welcomeText(organizationDTO.getWelcomeText())
                .urlFacebook(organizationDTO.getUrlFacebook())
                .urlInstagram(organizationDTO.getUrlInstagram())
                .urlLinkedin(organizationDTO.getUrlLinkedin())
                .creationTimestamp(entity.getCreationTimestamp())
                .updateTimestamp(new Date())
                .build();

    }
}