package com.alkemy.ong;

import com.alkemy.ong.model.Contact;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class ContactControllerTest extends ContactContextTest {

    private static final String CONTACT_PATH = "/contacts";
    
    @Before
    public void create() {
        if (contactRepository.findAll().isEmpty()) {
            createContact();
        }
    }

    @Test
    @WithUserDetails("admin")
    public void method_create_should_return_created_with_admin_role() throws Exception {
        mockMvc.perform(post(CONTACT_PATH)
                .content(createRequest(
                        "Juan Torres",
                        3254411,
                        "contact@test.com",
                        "this is a test message"
                ))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.name", equalTo("Juan Torres")))
                .andExpect(jsonPath("$.phone", equalTo(3254411)))
                .andExpect(jsonPath("$.email", equalTo("contact@test.com")))
                .andExpect(jsonPath("$.message", equalTo("this is a test message")))
                .andExpect(status().isCreated());
    }

    @Test
    @WithUserDetails("user")
    public void method_create_should_return_forbidden_with_user_role() throws Exception {
        mockMvc.perform(post(CONTACT_PATH)
                .content(createRequest(
                        "Juan Torres",
                        3254411,
                        "contact@test.com",
                        "this is a test message"
                ))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }
    @Test
    @WithUserDetails("user")
    public void method_getAll_should_return_ok_with_user_role() throws Exception {
        List<Contact> contacts = new ArrayList();
       mockMvc.perform(get(CONTACT_PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$..name", notNullValue()))
                .andExpect(jsonPath("$..email", notNullValue()))
                .andExpect(jsonPath("$..phone", notNullValue()))
                .andExpect(jsonPath("$..message", notNullValue()))
                .andExpect(status().isOk());
    }
}
