package com.alkemy.ong.controller;

import com.alkemy.ong.dto.OrganizationDto;
import com.alkemy.ong.dto.OrganizationUpdateDTO;
import com.alkemy.ong.dto.SlidesDto;
import com.alkemy.ong.dto.response.OrganizationResponseDTO;
import com.alkemy.ong.dto.response.SlideResponseDTO;
import com.alkemy.ong.service.OrganizationService;
import com.alkemy.ong.service.SlideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/organization")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private SlideService slideService;
    @Autowired
    private MessageSource messageSource;
    @GetMapping("/public")
    public ResponseEntity<?> getOrganizationsPublic () throws Exception {

        OrganizationDto organizationResponse = organizationService.getOrganizationPublic();
        if(organizationResponse==null)return ResponseEntity.notFound().build();
        List<SlideResponseDTO> slidesDtos = slideService.getAllSlidesById(organizationResponse.getId());
        OrganizationResponseDTO OrganizationDtoResponse=organizationService.getOrganizationResponseDTO(slidesDtos,organizationResponse);
        return ResponseEntity.status(HttpStatus.OK).body(OrganizationDtoResponse);
    }

    @PutMapping("/public")
    public ResponseEntity<OrganizationUpdateDTO> update (@Valid @RequestBody OrganizationUpdateDTO organizationUpdateDTO) {
        OrganizationUpdateDTO organizationUpdated = organizationService.updateOrganization(organizationUpdateDTO);
        return ResponseEntity.ok().body(organizationUpdated);
    }

}
