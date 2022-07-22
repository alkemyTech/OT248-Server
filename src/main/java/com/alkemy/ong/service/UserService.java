package com.alkemy.ong.service;

import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.model.Users;


import java.util.Map;

public interface UserService {
    public Users applyPatchToUser(long id, UserDto patch);
}
