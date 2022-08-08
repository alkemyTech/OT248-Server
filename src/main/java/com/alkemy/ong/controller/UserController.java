package com.alkemy.ong.controller;

import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.dto.response.UserResponseDTO;
import com.alkemy.ong.model.Users;
import com.alkemy.ong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAll(){
        List<UserResponseDTO> usersResponseList = userService.getAll();
        return ResponseEntity.ok().body(usersResponseList);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable Long id, @RequestBody UserDto patch) {
            Users user = userService.applyPatchToUser(id,patch);
            if(user==null)return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage() ,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

