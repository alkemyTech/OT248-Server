package com.alkemy.ong.service;

import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.dto.response.UserResponseDTO;
import com.alkemy.ong.model.Users;


import java.util.List;

public interface UserService {

    List<UserResponseDTO> getAll();

    Users applyPatchToUser(long id, UserDto patch);

    UserResponseDTO getUserDataByToken(String token);

    void deleteUser (Long id) throws Exception;

    UserResponseDTO findById(Long id);

    UserResponseDTO findByEmail(String email);
}
