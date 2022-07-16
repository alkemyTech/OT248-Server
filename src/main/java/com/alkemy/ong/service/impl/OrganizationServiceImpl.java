package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.OrganizationDto;
import com.alkemy.ong.model.Organization;
import com.alkemy.ong.repository.OrganizationRepository;
import com.alkemy.ong.service.OrganizationService;
import com.alkemy.ong.service.mapper.OrganizationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationMapper organizationMapper;

    @Autowired
    OrganizationRepository organizationRepository;

    @Override
    public OrganizationDto getOrganizationPublic(String name) {
        Optional<Organization> organization = organizationRepository.findByName(name);
        return organization.map(value -> organizationMapper.OrganizationEntityToDTO(value)).orElse(null);
    }


}



