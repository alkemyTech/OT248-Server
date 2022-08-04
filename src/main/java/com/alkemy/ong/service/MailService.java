
package com.alkemy.ong.service;

import java.io.IOException;

public interface MailService {

    public String sendEmail(String email) throws IOException;

    public String sendEmailRegisteredUser(String email) throws IOException;
    
    public String sendEmailCreatedContact(String email) throws IOException;
}