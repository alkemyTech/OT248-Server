package com.alkemy.ong.auth.controller;

import com.alkemy.ong.auth.dto.UserDTO;
import com.alkemy.ong.auth.service.UserDetailsCustomService;
import com.alkemy.ong.auth.utils.JwUtils;
import com.alkemy.ong.dto.response.UserResponseDTO;
import com.alkemy.ong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class UserAuthController {

    private UserDetailsCustomService userDetailsCustomService;
    private AuthenticationManager authenticationManager;
    private JwUtils jwUtils;

    @Autowired private UserService userService;

    @Autowired
    public UserAuthController(
            UserDetailsCustomService userDetailsCustomService,
            AuthenticationManager authenticationManager,
            JwUtils jwUtils) {
        this.userDetailsCustomService = userDetailsCustomService;
        this.authenticationManager = authenticationManager;
        this.jwUtils = jwUtils;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register (@Valid @RequestBody UserDTO user) throws Exception {

        UserDTO userResponse = userDetailsCustomService.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> getUserData(
            @RequestParam(name = "Authorization") String token
    ){
        return ResponseEntity.ok().body(userService.getUserDataByToken(token));
    }

}
