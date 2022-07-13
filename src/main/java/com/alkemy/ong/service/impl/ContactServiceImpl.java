package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.ContactDto;
import com.alkemy.ong.model.Contact;
import com.alkemy.ong.repository.ContactRepository;
import com.alkemy.ong.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;

public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public boolean isName(String name) {
        return contactRepository.existsByName(name);
    }

    @Override
    public boolean isPhone(Integer phone) {
        return contactRepository.existsByPhone(phone);
    }

    @Override
    public boolean isEmail(String email) {
        return contactRepository.existsByEmail(email);
    }

    @Override
    public ContactDto createContact(ContactDto contactDto) {
        Contact contact = mapEntity(contactDto);
        Contact newContact = contactRepository.save(contact);
        return mapDTO(newContact);
    }

    private ContactDto mapDTO(Contact contact) {
        ContactDto contactDto = new ContactDto();
        contactDto.setIdContact(contact.getIdContact());
        contactDto.setName(contact.getName());
        contactDto.setPhone(contact.getPhone());
        contactDto.setEmail(contact.getEmail());
        contactDto.setMessage(contact.getMessage());

        return contactDto;
    }

    private Contact mapEntity(ContactDto contactDto) {
        Contact contact = new Contact();
        contact.setIdContact(contactDto.getIdContact());
        contact.setName(contactDto.getName());
        contact.setPhone(contactDto.getPhone());
        contact.setEmail(contactDto.getEmail());
        contact.setMessage(contactDto.getMessage());

        return contact;
    }
}
