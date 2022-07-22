package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.response.UserResponseDTO;
import com.alkemy.ong.repository.UsersRepository;
import com.alkemy.ong.service.UsersService;
import com.alkemy.ong.service.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private UsersMapper usersMapper;

    public List<UserResponseDTO> getAll() {
        return usersMapper.userEntityListToDTOList(usersRepository.findAll());
    }
}
