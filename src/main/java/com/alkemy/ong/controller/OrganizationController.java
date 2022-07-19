package com.alkemy.ong.controller;

import com.alkemy.ong.dto.OrganizationDto;
import com.alkemy.ong.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/organization")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @GetMapping("/public")
    public ResponseEntity<OrganizationDto> getOrganizationsPublic () throws Exception {

        OrganizationDto organizationResponse = organizationService.getOrganizationPublic();
        if(organizationResponse==null)return ResponseEntity.notFound().build();
        return ResponseEntity.status(HttpStatus.OK).body(organizationResponse);
    }

}
