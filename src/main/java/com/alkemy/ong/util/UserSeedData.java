package com.alkemy.ong.util;

import com.alkemy.ong.model.Users;
import com.alkemy.ong.repository.RoleRepository;
import com.alkemy.ong.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserSeedData {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @EventListener
    public void eventListener(ContextRefreshedEvent contextRefreshedEvent) {
        if (userRepository.findAll().size() < 20) createUsers();
    }

    public void createUsers() {
        for (int i = 0; i < 20; i++) {
            if (i < 10) {
                userRepository.save(
                        Users.builder()
                                .email("user" + i + "@ongprueba.com")
                                .password("user12345")
                                .firstName("user" + i)
                                .lastName("test")
                                .role(roleRepository.findByName("ROLE_USER"))
                                .photo("image.jpg")
                                .createdOnTimestamp(new Date())
                                .build());
            } else {
                userRepository.save(
                        Users.builder()
                                .email("admin" + i + "@ongprueba.com")
                                .password("admin12345")
                                .firstName("admin" + i)
                                .lastName("test")
                                .role(roleRepository.findByName("ROLE_ADMIN"))
                                .photo("image.jpg")
                                .createdOnTimestamp(new Date())
                                .build());
            }
        }
    }

}
