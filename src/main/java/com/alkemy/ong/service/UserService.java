package com.alkemy.ong.service;

import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.dto.response.UserResponseDTO;
import com.alkemy.ong.model.Users;


import java.util.List;

public interface UserService {

    List<UserResponseDTO> getAll();

    Users applyPatchToUser(long id, UserDto patch);

    void deleteUser (Long id) throws Exception;

}
