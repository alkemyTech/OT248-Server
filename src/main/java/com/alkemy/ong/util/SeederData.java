package com.alkemy.ong.util;

import com.alkemy.ong.model.Role;
import com.alkemy.ong.model.Users;
import com.alkemy.ong.repository.RoleRepository;
import com.alkemy.ong.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SeederData {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired private UserRepository userRepository;

    @EventListener
    public void eventListener (ContextRefreshedEvent contextRefreshedEvent) {
        if (roleRepository.findAll().isEmpty()) createRole();
        if(userRepository.findAll().isEmpty()) createUsers();
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

    public void createUsers(){
        List<Role> roles = roleRepository.findAll();
        List<Users> usuarios = new ArrayList<>();
        usuarios.add(new Users("juan","perez",
                "juan@mail.com","1234","",roles.get(0)));
        usuarios.add(new Users("pepe","jults",
                "pepe@mail.com","1234","",roles.get(0)));
        usuarios.add(new Users("pedro","lopez",
                "pedro@mail.com","1234","",roles.get(0)));
        usuarios.add(new Users("ernesto","ford",
                "ernesto@mail.com","1234","",roles.get(0)));
        usuarios.add(new Users("john","sanchez",
                "john@mail.com","1234","",roles.get(0)));
        usuarios.add(new Users("samuel","perez",
                "samuel@mail.com","1234","",roles.get(0)));
        usuarios.add(new Users("ivan","andredde",
                "ivan@mail.com","1234","",roles.get(0)));
        usuarios.add(new Users("jose","martinez",
                "jose@mail.com","1234","",roles.get(0)));
        usuarios.add(new Users("oscar","bravo",
                "oscar@mail.com","1234","",roles.get(0)));
        usuarios.add(new Users("estavan","volarde",
                "estavan@mail.com","1234","",roles.get(0)));


        usuarios.add(new Users("peet","landino",
                "peet@mail.com","1234","",roles.get(1)));
        usuarios.add(new Users("mario","acosta",
                "mario@mail.com","1234","",roles.get(1)));
        usuarios.add(new Users("bruno","piñero",
                "bruno@mail.com","1234","",roles.get(1)));
        usuarios.add(new Users("rick","goya",
                "rick@mail.com","1234","",roles.get(1)));
        usuarios.add(new Users("mary","rincon",
                "mary@mail.com","1234","",roles.get(1)));
        usuarios.add(new Users("pepa","avila",
                "pepa@mail.com","1234","",roles.get(1)));
        usuarios.add(new Users("vilma","nuñes",
                "vilma@mail.com","1234","",roles.get(1)));
        usuarios.add(new Users("eddie","garcia",
                "eddie@mail.com","1234","",roles.get(1)));
        usuarios.add(new Users("zully","lubo",
                "zully@mail.com","1234","",roles.get(1)));
        usuarios.add(new Users("briget","lisboa",
                "briget@mail.com","1234","",roles.get(1)));

        userRepository.saveAll(usuarios);
    }

}
