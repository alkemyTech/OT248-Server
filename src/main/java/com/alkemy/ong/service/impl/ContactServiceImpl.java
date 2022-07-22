package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.ContactDto;
import com.alkemy.ong.model.Contact;
import com.alkemy.ong.repository.ContactRepository;
import com.alkemy.ong.service.ContactService;
import com.alkemy.ong.service.mapper.contact.MapContact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private MapContact mapContact;

    @Override
    public ContactDto createContact(ContactDto contactDto) {
        Contact contact = mapContact.mapEntity(contactDto);
        Contact newContact = contactRepository.save(contact);
        return mapContact.mapDTO(newContact);
    }
}
