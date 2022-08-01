package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.ContactDto;
import com.alkemy.ong.model.Contact;
import com.alkemy.ong.repository.ContactRepository;
import com.alkemy.ong.service.ContactService;
import com.alkemy.ong.service.mapper.contact.MapContact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private MapContact mapContact;

    @Override
    public ContactDto createContact(ContactDto contactDto) {
        Contact contact = mapContact.mapEntity(contactDto);
        contactRepository.save(contact);
        return contactDto;
    }

    @Override
    public List<ContactDto> searchAllContacts() {
        return contactRepository.findAll().stream()
                .map(x->  mapContact.mapDTO(x))
                .collect(toList());
    }


}