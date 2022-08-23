package com.alkemy.ong.User;

import com.alkemy.ong.InMemoryUserDetails;
import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.model.Role;
import com.alkemy.ong.model.Users;
import com.alkemy.ong.repository.RoleRepository;
import com.alkemy.ong.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = InMemoryUserDetails.class)
@AutoConfigureMockMvc
@Ignore
public class UserContextTest {

        @Autowired
        protected MockMvc mockMvc;
        private final ObjectMapper objectMapper = new ObjectMapper();
        @Autowired
        protected UserRepository userRepository;
    @Autowired
    protected RoleRepository roleRepository;
    protected String createRequest(String firstName, String lastName, String email, String password,
                                   String photo, Long role) throws JsonProcessingException {
        return objectMapper.writeValueAsString(UserDto.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(password)
                .photo(photo)
                .roleId(role)
                .build());
    }

        protected void createUsers(){
            userRepository.save(Users
                    .builder()
                    .firstName("Test firstName")
                    .lastName("Test lastName")
                    .email("firstName@firstName.com")
                    .password("465626865")
                    .photo("test.jpg")
                    .role(Role.builder()
                            .name("admin")
                            .description("test")
                            .id(1L)
                            .createAt(new Date())
                            .updateAt(new Date())
                            .build())
                    .id(1L)
                    .deleted(false)
                    .createdOnTimestamp(new Date())
                    .updatedOnTimestamp(new Date())
                    .build());
            userRepository.save(Users
                    .builder()
                    .firstName("Test firstName")
                    .lastName("Test lastName")
                    .email("lastName@firstName.com")
                    .password("4656268")
                    .photo("test.jpg")
                    .role(Role.builder()
                            .name("admin")
                            .description("test")
                            .id(1L)
                            .createAt(new Date())
                            .updateAt(new Date())
                            .build())
                    .id(2L)
                    .deleted(false)
                    .createdOnTimestamp(new Date())
                    .updatedOnTimestamp(new Date())
                    .build());
            roleRepository.save(Role.builder()
                    .id(1L)
                    .name("test")
                    .description("test")
                    .createAt(new Date())
                    .updateAt(new Date())
                    .build());
        }
    }
