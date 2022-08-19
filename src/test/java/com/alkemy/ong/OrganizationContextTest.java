package com.alkemy.ong;

import com.alkemy.ong.dto.OrganizationUpdateDTO;
import com.alkemy.ong.model.Organization;
import com.alkemy.ong.repository.OrganizationRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Ignore;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = InMemoryUserDetails.class)
@AutoConfigureMockMvc
@Ignore
public class OrganizationContextTest {

    @Autowired
    protected MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    protected OrganizationRepository organizationRepository;

    protected String createRequest(String name, String image, String address, Integer phone,
                                   String email, String welcomeText, String aboutUsText,
                                   String urlFacebook, String urlInstagram, String urlLinkedin) throws JsonProcessingException {

        return objectMapper.writeValueAsString(OrganizationUpdateDTO.builder()
                .name(name)
                .image(image)
                .address(address)
                .phone(phone)
                .email(email)
                .welcomeText(welcomeText)
                .aboutUsText(aboutUsText)
                .urlFacebook(urlFacebook)
                .urlInstagram(urlInstagram)
                .urlLinkedin(urlLinkedin)
                .build());
    }

    protected void createOrganization(){
        organizationRepository.save(Organization
                .builder()
                .name("Organization Test")
                .image("imageTest")
                .address("Address Test")
                .phone(465626865)
                .email("mail@test.com")
                .welcomeText("Welcome")
                .aboutUsText("About organization test")
                .build());
    }


}
