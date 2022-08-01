package com.alkemy.ong.service;

import com.alkemy.ong.dto.ContactDto;

import java.util.List;

public interface ContactService {

    public ContactDto createContact(ContactDto contactDto);

    List<ContactDto> searchAllContacts();
}
