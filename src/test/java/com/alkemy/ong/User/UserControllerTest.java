package com.alkemy.ong.User;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class UserControllerTest extends UserContextTest {

    private static final String USERS_PATH = "/users";

    @BeforeEach
    public void create (){
        createUsers();
    }

    @Test
    @WithUserDetails("admin")
    public void should_return_OK_status_code_when_try_to_get_the_users () throws Exception {
        mockMvc.perform(get(USERS_PATH).contentType(APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$[0].name",notNullValue()))
                .andExpect(jsonPath("$[0].lastName",notNullValue()))
                .andExpect(jsonPath("$[0].email",notNullValue()))
                .andExpect(jsonPath("$[0].id",notNullValue()))
                .andExpect(jsonPath("$[0].photo",notNullValue()))
                .andExpect(jsonPath("$[1].name",notNullValue()))
                .andExpect(jsonPath("$[1].lastName",notNullValue()))
                .andExpect(jsonPath("$[1].email",notNullValue()))
                .andExpect(jsonPath("$[1].id",notNullValue()))
                .andExpect(jsonPath("$[1].photo",notNullValue()))
                .andExpect(status().isOk());
    }
    @Test
    @WithUserDetails()
    public void should_return_FORBIDDEN_status_code_if_try_get_users_with_role_user() throws Exception {
        mockMvc.perform(get(USERS_PATH).contentType(APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("admin")
    public void should_return_OK_status_code_when_try_to_delete_the_user() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete((USERS_PATH)+"/{id}", "1")
                        .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }
    @Test
    @WithUserDetails("user")
    public void testFooDelete() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete((USERS_PATH)+"/1")
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("admin")
    public void should_return_OK_status_code_when_try_to_patch_the_user () throws Exception {
        mockMvc.perform(patch((USERS_PATH)+"/2").contentType(APPLICATION_JSON).
                        content(createRequest("nameTest",
                                "nameTest",
                                "email@test.ong",
                                "4656268",
                                "dgfgdfgd.jpg",
                                2L))
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.name",notNullValue()))
                .andExpect(jsonPath("$.lastName",notNullValue()))
                .andExpect(jsonPath("$.email",notNullValue()))
                .andExpect(jsonPath("$.id",notNullValue()))
                .andExpect(jsonPath("$.photo",notNullValue()))
                .andExpect(status().isOk());
    }
    @Test
    @WithUserDetails()
    public void should_return_FORBIDDEN_status_code_if_try_patch_users_with_role_user() throws Exception {
        mockMvc.perform(patch((USERS_PATH)+"/1").contentType(MediaType.APPLICATION_JSON).
                        content(createRequest("nameTest",
                                "imageTest",
                                "addressTest",
                                "12345678",
                                "email@test.ong",
                                1L))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }
}
