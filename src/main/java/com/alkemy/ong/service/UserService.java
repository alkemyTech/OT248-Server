package com.alkemy.ong.service;

import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.dto.response.UserResponseDTO;
import com.alkemy.ong.model.Users;


import java.util.List;
import java.util.Map;

public interface UserService {

    List<UserResponseDTO> getAll();

    public Users applyPatchToUser(long id, UserDto patch);

    public UserResponseDTO getUserDataByToken(String token);

}
