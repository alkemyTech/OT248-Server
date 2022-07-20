package com.alkemy.ong.service.mapper;

import com.alkemy.ong.dto.response.UserResponseDTO;
import com.alkemy.ong.model.Users;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UsersMapper {


    public UserResponseDTO userEntityToDTO (Users user) {
        return UserResponseDTO
                .builder()
                .name(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .photo(user.getPhoto())
                .createdOn(user.getCreatedOnTimestamp())
                .updateOn(user.getUpdatedOnTimestamp())
                .build();
    }

    public List<UserResponseDTO> userEntityListToDTOList (List<Users> usersEntity) {
        List<UserResponseDTO> userResponseDTOList = new ArrayList<>();
        for (Users users: usersEntity) {
            userResponseDTOList.add(userEntityToDTO(users));
        }
        return userResponseDTOList;
    }

}
