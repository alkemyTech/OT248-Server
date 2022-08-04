package com.alkemy.ong.service;

import com.alkemy.ong.dto.ContactDto;
import java.io.IOException;

public interface ContactService {

    public ContactDto createContact(ContactDto contactDto) throws IOException ;
}
