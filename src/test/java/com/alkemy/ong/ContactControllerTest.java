/*package com.alkemy.ong;

import com.alkemy.ong.controller.ContactController;
import com.alkemy.ong.model.Contact;
import com.alkemy.ong.repository.ContactRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.mockito.*;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;



@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
public class ContactControllerTest {

    
    private MockMvc mockMvc;

    @Mock
    private ContactRepository contactRepository;

    @InjectMocks
    private ContactController contactController;
    
    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();
    
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(contactController).build();
        
    }
//    private String email = "contact@test.com";
//    private String name = "Juan Torres";
//    private Integer phone = 3254411;
//    private String message = "this is a test message";

    @Test
    @WithUserDetails("admin")
    public void createContactSuccess() throws Exception {
        Contact contact = Contact.builder()
                                 .email("contact@test.com")
                                 .name("Juan Torres")
                                 .phone(3254411)
                                 .message("this is a test message")
                                 .build();
        
        Mockito.when(contactRepository.save(contact)).thenReturn(contact);
        
        String content = objectWriter.writeValueAsString(contact);
        
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/contacts")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);
        
        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath(".$", notNullValue()))
                .andExpect(jsonPath(".name", is("Juan Torres")))
                .andExpect(jsonPath(".email", is("contact@test.com")))
                .andExpect(jsonPath(".phone", is(3254411)));
                
                
                
               
    }
}*/
