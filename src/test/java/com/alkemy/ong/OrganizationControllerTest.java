package com.alkemy.ong;


import com.alkemy.ong.model.Organization;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class OrganizationControllerTest extends OrganizationContextTest {

    private static final String ORGANIZATION_PATH = "/organization";

    @Before
    public void create (){
        if (organizationRepository.findAll().isEmpty()) createOrganization();
    }

    @Test
    @WithUserDetails("admin")
    public void put_organization_with_admin_role_OK() throws Exception {
        mockMvc.perform(put(ORGANIZATION_PATH + "/public")
                        .content(createRequest("nameTest",
                                "imageTest",
                                "addressTest",
                                12345678,
                                "email@test.ong",
                                "welcomeText",
                                "aboutUsText",
                                "Facebook",
                                "Instagram",
                                "Linkedin"))
                        .contentType(APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.name", equalTo("nameTest")))
                .andExpect(jsonPath("$.image", equalTo("imageTest")))
                .andExpect(jsonPath("$.phone", equalTo(12345678)))
                .andExpect(jsonPath("$.address", equalTo("addressTest")))
                .andExpect(jsonPath("$.welcomeText", equalTo("welcomeText")))
                .andExpect(jsonPath("$.aboutUsText", equalTo("aboutUsText")))
                .andExpect(jsonPath("$.urlFacebook", equalTo("Facebook")))
                .andExpect(jsonPath("$.urlInstagram", equalTo("Instagram")))
                .andExpect(jsonPath("$.urlLinkedin", equalTo("Linkedin")))
                .andExpect(status().isOk());
    }


    @Test
    @WithUserDetails
    public void should_return_OK_status_code_when_try_to_get_the_organization () throws Exception {
        mockMvc.perform(get(ORGANIZATION_PATH + "/public").contentType(APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.name", notNullValue()))
                .andExpect(jsonPath("$.image", notNullValue()))
                .andExpect(jsonPath("$.phone", notNullValue()))
                .andExpect(jsonPath("$.address", notNullValue()))
                .andExpect(status().isOk());
    }

    // ---------- ERROR CODE ------------

    @Test
    @WithUserDetails("user")
    public void should_return_FORBIDDEN_status_code_if_try_put_an_organization_with_role_user() throws Exception {
        mockMvc.perform(put(ORGANIZATION_PATH + "/public")
                        .content(createRequest("nameTest",
                                "imageTest",
                                "addressTest",
                                12345678,
                                "email@test.ong",
                                "welcomeText",
                                "aboutUsText",
                                "Facebook",
                                "Instagram",
                                "Linkedin"))
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }


}
