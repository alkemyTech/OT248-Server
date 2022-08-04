package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.ContactDto;
import com.alkemy.ong.model.Contact;
import com.alkemy.ong.repository.ContactRepository;
import com.alkemy.ong.service.ContactService;
import com.alkemy.ong.service.MailService;
import com.alkemy.ong.service.mapper.contact.MapContact;
import java.io.IOException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private MapContact mapContact;

    @Autowired
    private MailService mailService;

    @Autowired
    private MessageSource messageSource;

    @Override
    public ContactDto createContact(ContactDto contactDto) throws IOException {
        Contact contact = mapContact.mapEntity(contactDto);
        Contact newContact = contactRepository.save(contact);
        try {
            mailService.sendEmailCreatedContact(newContact.getEmail());

        } catch (IOException ex) {
            throw new IOException(messageSource.getMessage("error.sendMail.notFound", null, Locale.US));
        } finally {
            return mapContact.mapDTO(newContact);
        }

    }

    @Override
    public List<ContactDto> searchAllContacts() {
        return contactRepository.findAll().stream()
                .map(x->  mapContact.mapDTO(x))
                .collect(toList());
    }


}