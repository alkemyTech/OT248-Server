package com.alkemy.ong.auth.controller;

import com.alkemy.ong.auth.dto.request.AuthenticationRequest;
import com.alkemy.ong.auth.dto.request.UserDTO;
import com.alkemy.ong.auth.dto.response.Jwt;
import com.alkemy.ong.auth.service.UserDetailsCustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class UserAuthController {

    @Autowired
    private UserDetailsCustomService userDetailsCustomService;

    @PostMapping("/register")
    public ResponseEntity<Jwt> register(@Valid @RequestBody UserDTO user) throws Exception {

        Jwt jwt = userDetailsCustomService.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(jwt);
    }

    @PostMapping("/login")
    public ResponseEntity<Jwt> login(@Valid @RequestBody AuthenticationRequest authenticationRequest) {

        Jwt jwt = userDetailsCustomService.authentication(authenticationRequest);

        return ResponseEntity.ok(jwt);

    }
}
