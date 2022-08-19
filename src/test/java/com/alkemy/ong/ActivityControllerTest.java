package com.alkemy.ong;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ActivityControllerTest extends ActivityContextTest{

    private static final String ACTIVITY_PATH = "/activities";

    @Before
    public void create(){
        if(activityRepository.findAll().isEmpty()){
            createActivity();
        }
    }

    @Test
    @WithUserDetails("admin")
    public void post_method_should_return_ok_with_admin_role() throws Exception{
        mockMvc.perform(post(ACTIVITY_PATH)
                .content(createRequest(
                        "Actividad 1",
                        "contenido",
                        "imgurl"
                ))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.name", equalTo("Actividad 1")))
                .andExpect(jsonPath("$.content", equalTo("contenido")))
                .andExpect(jsonPath("$.image", equalTo("imgurl")))
                .andExpect(status().isOk());
    }
    @Test
    @WithUserDetails("user")
    public void post_method_should_return_forbidden_with_user_role() throws Exception{
        mockMvc.perform(post(ACTIVITY_PATH)
                        .content(createRequest(
                                "Actividad 1",
                                "contenido",
                                "imgurl"
                        ))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("admin")
    public void put_method_should_return_ok_with_admin_role() throws Exception{
        mockMvc.perform(put(ACTIVITY_PATH + "/{id}", "1")
                        .content(createRequest(
                                "Actividad 2",
                                "contenido",
                                "imgurl"
                        ))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.name", equalTo("Actividad 2")))
                .andExpect(jsonPath("$.content", equalTo("contenido")))
                .andExpect(jsonPath("$.image", equalTo("imgurl")))
                .andExpect(status().isOk());
    }

    @Test
    @WithUserDetails("user")
    public void put_method_should_return_Forbidden_with_user_role() throws Exception{
        mockMvc.perform(put(ACTIVITY_PATH + "/{id}", "1")
                        .content(createRequest(
                                "Actividad 2",
                                "contenido",
                                "imgurl"
                        ))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

}
