package com.alkemy.ong.controller;

import com.alkemy.ong.dto.ContactDto;
import com.alkemy.ong.service.ContactService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping()
    public ResponseEntity<?> create(@Valid @RequestBody ContactDto contactDto, BindingResult result){
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(contactService.createContact(contactDto));
    }

    @GetMapping()
    public ResponseEntity<?> getAllContacts (){
        List<ContactDto> contactsDto = contactService.searchAllContacts();
        if(contactsDto==null || contactsDto.isEmpty())return ResponseEntity.notFound().build();
        return ResponseEntity.status(HttpStatus.OK).body(contactsDto);
    }

}