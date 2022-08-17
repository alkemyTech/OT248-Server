package com.alkemy.ong;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.List;

@TestConfiguration
public class InMemoryUserDetails {

    @Bean
    @Primary
    public UserDetailsService createUsers() {

        UserDetails user = builderUser("user", "USER");
        UserDetails admin = builderUser("admin", "ADMIN");

        return new InMemoryUserDetailsManager(List.of(user, admin));
    }

    private UserDetails builderUser(String username, String role) {
        return User.withUsername(username).password("password").roles(role).build();
    }
}
