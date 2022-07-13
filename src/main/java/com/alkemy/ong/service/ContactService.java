package com.alkemy.ong.service;

import com.alkemy.ong.dto.ContactDto;

public interface ContactService {

    public boolean isName(String name);
    public boolean isPhone(Integer phone);
    public boolean isEmail(String email);

    public ContactDto createContact(ContactDto contactDto);
}
