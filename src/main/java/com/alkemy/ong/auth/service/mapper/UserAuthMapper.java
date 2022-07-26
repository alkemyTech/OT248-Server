package com.alkemy.ong.auth.service.mapper;

import com.alkemy.ong.auth.dto.request.UserDTO;
import com.alkemy.ong.model.Users;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UserAuthMapper {

    public UserDTO userEntityToDTO(Users user) {
        return UserDTO.builder()
                .email(user.getEmail())
                .name(user.getFirstName())
                .lastName(user.getLastName())
                .password(user.getPassword())
                .photo(user.getPhoto())
                .build();
    }

    public Users userDTOtoEntity(UserDTO userDTO) {
        return Users.builder()
                .email(userDTO.getEmail())
                .firstName(userDTO.getName())
                .lastName(userDTO.getLastName())
                .photo(userDTO.getPhoto())
                .createdOnTimestamp(new Date())
                .build();
    }


}
