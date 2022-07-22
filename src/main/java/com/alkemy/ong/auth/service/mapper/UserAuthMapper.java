package com.alkemy.ong.auth.service.mapper;

import com.alkemy.ong.auth.dto.UserDTO;
import com.alkemy.ong.model.Users;
import org.springframework.stereotype.Component;

@Component
public class UserAuthMapper {

    public UserDTO userEntityToDTO (Users user){
        UserDTO userDTO = UserDTO
                .builder()
                .email(user.getEmail())
                .name(user.getFirstName())
                .lastName(user.getLastName())
                .password(user.getPassword())
                .photo(user.getPhoto())
                .build();

        return userDTO;
    }



}
