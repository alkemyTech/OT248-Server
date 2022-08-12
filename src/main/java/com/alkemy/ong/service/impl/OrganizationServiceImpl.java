package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.OrganizationDto;
import com.alkemy.ong.dto.OrganizationUpdateDTO;
import com.alkemy.ong.dto.SlidesDto;
import com.alkemy.ong.dto.response.OrganizationResponseDTO;
import com.alkemy.ong.dto.response.SlideResponseDTO;
import com.alkemy.ong.model.Organization;
import com.alkemy.ong.repository.OrganizationRepository;
import com.alkemy.ong.service.OrganizationService;
import com.alkemy.ong.service.mapper.OrganizationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationMapper organizationMapper;

    @Autowired
    private OrganizationRepository organizationRepository;

    @Override
    public OrganizationDto getOrganizationPublic() {
        Optional<Organization> organization = organizationRepository.findFirstByOrderByIdOrganization();
        return organization.map(value -> organizationMapper.OrganizationEntityToDTO(value)).orElse(null);
    }

    @Transactional
    public OrganizationUpdateDTO updateOrganization(OrganizationUpdateDTO organizationUpdateDTO) {
        /* Validate if exists in DB */
        Optional<Organization> organizationEntity = organizationRepository.findFirstByOrderByIdOrganization();
        if (organizationEntity.isEmpty()) throw new EntityNotFoundException("Organization is not present.");
        /* Update */
        Organization organization = organizationEntity.get(); /* I do it in two steps for get the id and creation date */
        organization = organizationMapper.organizationDTOToEntity(organizationUpdateDTO);
        /* Save and turn the updated entity into DTO and return it */
        return organizationMapper
                .organizationEntityToOrganizationUpdateDTO(organizationRepository
                        .save(organization));
    }

    @Override
    public OrganizationResponseDTO getOrganizationResponseDTO(List<SlideResponseDTO> slidesDtos, OrganizationDto organizationResponse) {
        OrganizationResponseDTO organizationResponseDTO=organizationMapper.organizationDTOToResponseDto(organizationResponse);
        organizationResponseDTO.setSlides(slidesDtos);
        return organizationResponseDTO;
    }


}



