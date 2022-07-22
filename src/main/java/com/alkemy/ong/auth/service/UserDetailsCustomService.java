package com.alkemy.ong.auth.service;

import com.alkemy.ong.auth.dto.UserDTO;
import com.alkemy.ong.auth.service.mapper.UserAuthMapper;
import com.alkemy.ong.model.Users;
import com.alkemy.ong.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;

@Service
public class UserDetailsCustomService implements UserDetailsService {

    @Autowired
    UserAuthMapper userMapper;

    @Autowired
    private UserRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user = usersRepository.findByEmail(email);
        if(user == null) throw new UsernameNotFoundException("Username not found");
        return new User(user.getEmail(), user.getPassword(), Collections.emptyList());
    }

    public UserDTO save (UserDTO userDTO){
        String encryptPass = passwordEncoder.encode(userDTO.getPassword());
        Users user = Users
                .builder()
                .email(userDTO.getEmail())
                .password(encryptPass)
                .firstName(userDTO.getName())
                .lastName(userDTO.getLastName())
                .photo(userDTO.getPhoto())
                .createdOnTimestamp(new Date())
                .build();
        UserDTO userResponse = userMapper.userEntityToDTO(usersRepository.save(user));

        return userResponse;
    }

}
