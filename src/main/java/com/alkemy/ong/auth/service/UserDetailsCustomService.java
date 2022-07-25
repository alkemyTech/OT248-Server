package com.alkemy.ong.auth.service;

import com.alkemy.ong.auth.dto.Jwt;
import com.alkemy.ong.auth.dto.UserDTO;
import com.alkemy.ong.auth.service.mapper.UserAuthMapper;
import com.alkemy.ong.auth.utils.JwUtils;
import com.alkemy.ong.model.Role;
import com.alkemy.ong.model.Users;
import com.alkemy.ong.repository.RoleRepository;
import com.alkemy.ong.repository.UserRepository;
import com.amazonaws.services.kms.model.AlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserDetailsCustomService implements UserDetailsService {

    @Autowired
    private UserAuthMapper userMapper;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwUtils jwUtils;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Users user = userRepository.findByEmail(email);
        if(user == null) throw new UsernameNotFoundException("Username not found");

        Collection<GrantedAuthority> authorities = Collections
                .singleton(new SimpleGrantedAuthority(user.getRole().getName()));

        return new User(user.getEmail(), user.getPassword(), authorities);

    }

    public Jwt save (UserDTO userDTO){
        String encryptPass = passwordEncoder.encode(userDTO.getPassword());
        if (userRepository.findByEmail(userDTO.getEmail()) != null) throw new AlreadyExistsException("Email is already in use");
        Users user = userMapper.userDTOtoEntity(userDTO);
        user.setPassword(encryptPass);
        user.setRole(roleRepository.findByName("ROLE_USER"));
        userRepository.save(user);

        String jwt = jwUtils.generateToken(loadUserByUsername(user.getEmail()));

        return Jwt.builder()
                .token(jwt)
                .build();
    }
}
