package com.alkemy.ong;

import com.alkemy.ong.dto.ContactDto;
import com.alkemy.ong.model.Contact;
import com.alkemy.ong.repository.ContactRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = InMemoryUserDetails.class)
@AutoConfigureMockMvc
public class ContactContextTest {

    @Autowired
    protected MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    protected ContactRepository contactRepository;

    protected String createRequest(String name, Integer phone, String email, String message) throws JsonProcessingException {
        return objectMapper.writeValueAsString(ContactDto.builder()
                .name(name)
                .phone(phone)
                .email(email)
                .message(message)
                .build()
        );
    }

    protected void createContact() {
        contactRepository.save(Contact.builder()
                .email("contact@test.com")
                .name("Juan Torres")
                .phone(3254411)
                .message("this is a test message")
                .build());
    }
}
