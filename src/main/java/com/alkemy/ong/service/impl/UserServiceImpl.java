package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.dto.response.UserResponseDTO;
import com.alkemy.ong.model.Users;
import com.alkemy.ong.repository.UserRepository;
import com.alkemy.ong.service.UserService;
import com.alkemy.ong.service.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UsersMapper usersMapper;

    public List<UserResponseDTO> getAll() {
        return usersMapper.userEntityListToDTOList(userRepository.findAll());
    }

    @Override
    public Users applyPatchToUser(long id, UserDto patch) {
        Optional<Users> user = userRepository.findById(id);
        if (user.isEmpty()) return null;
        user.ifPresent(userTemp->{
                    userTemp.setFirstName(patch.getFirstName());
                    userTemp.setLastName(patch.getLastName());
                    userTemp.setPhoto(patch.getPhoto());
                    userTemp.setRoleId(patch.getRoleId());
                    userTemp.setEmail(patch.getEmail());
                    userTemp.setPassword(patch.getPassword());
                });
        return userRepository.save(user.get());
       }

}



