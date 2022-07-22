package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.model.Users;
import com.alkemy.ong.repository.RoleRepository;
import com.alkemy.ong.repository.UsersRepository;
import com.alkemy.ong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Users applyPatchToUser(long id, UserDto patch) {
        Optional<Users> user = usersRepository.findById(id);
        if (user.isEmpty()) return null;
        user.ifPresent(userTemp->{
                    userTemp.setFirstName(patch.getFirstName());
                    userTemp.setLastName(patch.getLastName());
                    userTemp.setPhoto(patch.getPhoto());
                    userTemp.setRole(roleRepository.findById(patch.getRoleId()).get());
                    userTemp.setEmail(patch.getEmail());
                    userTemp.setPassword(patch.getPassword());
                });
        return usersRepository.save(user.get());
       }

}



