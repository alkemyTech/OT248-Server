package com.alkemy.ong.service.mapper.contact;

import com.alkemy.ong.dto.ContactDto;
import com.alkemy.ong.model.Contact;
import org.springframework.stereotype.Component;

@Component
public class MapContact {

    public ContactDto mapDTO(Contact contact) {
        ContactDto contactDto = new ContactDto();
        contactDto.setIdContact(contact.getIdContact());
        contactDto.setName(contact.getName());
        contactDto.setPhone(contact.getPhone());
        contactDto.setEmail(contact.getEmail());
        contactDto.setMessage(contact.getMessage());

        return contactDto;
    }

    public Contact mapEntity(ContactDto contactDto) {
        Contact contact = new Contact();
        contact.setIdContact(contactDto.getIdContact());
        contact.setName(contactDto.getName());
        contact.setPhone(contactDto.getPhone());
        contact.setEmail(contactDto.getEmail());
        contact.setMessage(contactDto.getMessage());

        return contact;
    }
}
