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
    public ResponseEntity<OrganizationDto> getOrganizationsPublic (@Valid @RequestBody OrganizationDto organizationDto,@RequestParam("name") String name) throws Exception {

        OrganizationDto OrganizationResponse = organizationService.getOrganizationPublic(name);
        if(OrganizationResponse==null)return ResponseEntity.notFound().build();
        return ResponseEntity.status(HttpStatus.OK).body(OrganizationResponse);
    }

}
