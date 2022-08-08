package com.alkemy.ong.repository;


import com.alkemy.ong.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    Users findByEmail(String email);

    Optional<Users> findById(long id);

    Users save(Users user);

    public Users findByFirstName(String firstName);
    
}