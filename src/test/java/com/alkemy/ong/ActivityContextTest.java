package com.alkemy.ong;

import com.alkemy.ong.dto.ActivityRequestDTO;
import com.alkemy.ong.model.Activity;
import com.alkemy.ong.repository.ActivityRepository;
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
public class ActivityContextTest {

    @Autowired
    protected MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    protected ActivityRepository activityRepository;

    protected String createRequest(String name, String content, String image) throws JsonProcessingException {
        return objectMapper.writeValueAsString(ActivityRequestDTO.builder()
                .name(name)
                .content(content)
                .image(image)
                .build()
        );
    }

    protected void createActivity(){
        activityRepository.save(Activity.builder()
                .name("Actividad 1")
                .content("contenido")
                .image("imagen")
                .build());
    }
}
