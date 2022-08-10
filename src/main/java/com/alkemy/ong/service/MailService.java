package com.alkemy.ong.service;

import java.io.IOException;

public interface MailService {

    String sendEmail(String email) throws IOException;

    String sendEmailCreatedContact(String email) throws IOException;

    String sendEmailRegisteredUser(String email) throws IOException;

}
