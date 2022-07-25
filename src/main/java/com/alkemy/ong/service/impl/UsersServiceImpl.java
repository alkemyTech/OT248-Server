
package com.alkemy.ong.service.impl;

import com.alkemy.ong.model.Users;
import com.alkemy.ong.repository.UserRepository;
import com.alkemy.ong.service.UsersService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UserRepository userRepository;
    
    
    @Override
    public void deleteUser(Long id) throws Exception{
        Optional<Users> response = userRepository.findById(id);
        if (response.isPresent()) {
            Users users = response.get();
            userRepository.delete(users);
        }else{
            throw new Exception("a user with that id was not found");
        }
        
        
    }
    
}
