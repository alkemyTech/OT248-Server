package com.alkemy.ong.util;

import com.alkemy.ong.model.Role;
import com.alkemy.ong.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SeederData {

    @Autowired
    private RoleRepository roleRepository;

    @EventListener
    public void eventListener (ContextRefreshedEvent contextRefreshedEvent) {
        if (roleRepository.findAll().isEmpty()) createRole();
    }

    public void createRole() {
        String[] roles = {"ROLE_USER", "ROLE_ADMIN"};
        for (int i = 0; i < roles.length; i++) {
            roleRepository.save(
                    Role.builder()
                            .name(roles[i])
                            .description("")
                            .createAt(new Date())
                            .build());
        }
    }

}
