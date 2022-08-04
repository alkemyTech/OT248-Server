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

    String[] names = {"Juan", "Roberto", "Manuel", "Emanuel", "José",
            "Ernesto", "Cintia", "Elisa", "María", "Josefina",
            "Florencia", "Aylen", "Walter", "Jesse", "Saul", "Skyler",
            "Walter Jr", "Maximiliano", "Pablo", "Hector"};
    String[] lastNames = {"Morales", "Perez", "Gonzales", "Hil", "Paz",
            "Sabati", "Muñoz", "Machado", "Palacios", "Pilas",
            "Castelucci", "Mendez", "White", "Pinkman", "Goodman", "White",
            "White", "Falcone", "Falcone", "Gonzales"};
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
                                .email((names[i].toLowerCase() + lastNames[i].toLowerCase() + "@ongprueba.com").replace(" ", ""))
                                .password(names[i].trim() + "12345")
                                .firstName(names[i])
                                .lastName(lastNames[i])
                                .role(roleRepository.findByName("ROLE_USER"))
                                .photo(names[i] + ".jpg")
                                .createdOnTimestamp(new Date())
                                .build());
            } else {
                userRepository.save(
                        Users.builder()
                                .email((names[i].toLowerCase() + lastNames[i].toLowerCase() + "@ongprueba.com").replace(" ", ""))
                                .password(names[i].trim() + "12345")
                                .firstName(names[i])
                                .lastName(lastNames[i])
                                .role(roleRepository.findByName("ROLE_ADMIN"))
                                .photo(names[i] + ".jpg")
                                .createdOnTimestamp(new Date())
                                .build());
            }
        }
    }

}
