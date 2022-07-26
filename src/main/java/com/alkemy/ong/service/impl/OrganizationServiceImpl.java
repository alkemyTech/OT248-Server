package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.OrganizationDto;
import com.alkemy.ong.dto.OrganizationUpdateDTO;
import com.alkemy.ong.model.Organization;
import com.alkemy.ong.repository.OrganizationRepository;
import com.alkemy.ong.service.OrganizationService;
import com.alkemy.ong.service.mapper.OrganizationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.Optional;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationMapper organizationMapper;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Override
    public OrganizationDto getOrganizationPublic( ) {
        Optional<Organization> organization = organizationRepository.findFirstByOrderByIdOrganization();
        return organization.map(value -> organizationMapper.OrganizationEntityToDTO(value)).orElse(null);
    }

    @Transactional
    public OrganizationUpdateDTO updateOrganization(OrganizationUpdateDTO organizationUpdateDTO) {
        /* Validate if exists in DB */
        Optional<Organization> organizationEntity = organizationRepository.findFirstByOrderByIdOrganization();
        if (organizationEntity.isEmpty()) throw new EntityNotFoundException("Organization is not present.");
        /* Update */
        Organization organization = Organization.builder()
                .idOrganization(organizationEntity.get().getIdOrganization())
                .name(organizationUpdateDTO.getName())
                .address(organizationUpdateDTO.getAddress())
                .phone(organizationUpdateDTO.getPhone())
                .image(organizationUpdateDTO.getImage())
                .aboutUsText(organizationUpdateDTO.getAboutUsText())
                .welcomeText(organizationUpdateDTO.getWelcomeText())
                .creationTimestamp(organizationEntity.get().getCreationTimestamp())
                .updateTimestamp(new Date())
                .build();
        /* Save and turn the updated entity into DTO and return it */
        return organizationMapper
                .organizationEntityToOrganizationUpdateDTO(organizationRepository
                        .save(organization));
    }


}



