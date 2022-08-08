package com.alkemy.ong.service.impl;

import com.alkemy.ong.service.MailService;
import com.alkemy.ong.util.DynamicTemplatePersonalization;
import com.sendgrid.*;
import com.alkemy.ong.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MailServiceImpl implements MailService {

    private static final Logger logger = LoggerFactory.getLogger(MailService.class);

    @Value("${api.sendGrid.java}")
    private String sendGridApiKey;

    @Value("${api.sendGrid.dynamicTemplateId}")
    private String dynamicTemplateId;

    @Autowired
    private DynamicTemplatePersonalization personalization;

    @Override
    public String sendEmail(String email) throws IOException {
        Email from = new Email(Constants.SEND_GRID_SENDER_EMAIL);
        String subject = Constants.SEND_GRID_WELCOME;
        Email to = new Email(email);
        Content content = new Content(Constants.SEND_GRID_TYPE, Constants.SEND_GRID_MESSAGE);
        Mail mail = new Mail(from, subject, to, content);
        return sendGivenMail(mail);
    }


    @Override
    public String sendEmailRegisteredUser(String email) throws IOException {
        Email from = new Email(Constants.SEND_GRID_SENDER_EMAIL);
        String subject = Constants.SEND_GRID_WELCOME;
        Email to = new Email(email);
        Mail mail = new Mail();
        personalization.clearData();
        personalization.addTo(to);
        mail.setFrom(from);
        mail.setSubject(subject);
        personalization.addDynamicTemplateData("title", Constants.SEND_GRID_TEMPLATE_TITLE);
        personalization.addDynamicTemplateData("text", Constants.SEND_GRID_TEMPLATE_MESSAGE);
        mail.addPersonalization(personalization);
        mail.setTemplateId(dynamicTemplateId);
        return sendGivenMail(mail);
    }

    private String sendGivenMail(Mail mail) throws IOException {
        SendGrid sendGrid = new SendGrid(sendGridApiKey);
        Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint(Constants.SEND_GRID_ENDPOINT);
        request.setBody(mail.build());
        Response response = sendGrid.api(request);
        logger.info(response.getBody());
        return response.getBody();
    }

}