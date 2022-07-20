package com.alkemy.ong.service;

import com.alkemy.ong.dto.response.UserResponseDTO;
import java.util.List;

public interface UsersService {

    List<UserResponseDTO> getAll();

}
