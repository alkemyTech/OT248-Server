package com.alkemy.ong.service.impl;

import com.alkemy.ong.model.Users;
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

    @Override
    public Users applyPatchToUser(long id, Map<Object,Object> patch) {
        Optional<Users> user = usersRepository.findById(id);
        if (user.isEmpty()) return null;
        user.ifPresent(userTemp -> patch.forEach((key, value) ->
        {
            Field field = ReflectionUtils.findField(Users.class, (String) key);
            assert field != null;
            field.setAccessible(true);
            ReflectionUtils.setField(field, userTemp, value);
        }));
        usersRepository.save(user.get());
        return user.get();
       }

}



