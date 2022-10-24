package com.init.users.dao;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.*;

import com.init.users.entity.userEntity;

public interface UserDao extends JpaRepository<userEntity, Long>{
    
    @Query(value = "DELETE FROM users WHERE users.email = ?1", nativeQuery = true)
    Void deleteBy(String email);
    
    @Query(value = "SELECT * FROM users WHERE users.email = ?1 AND users.password = ?2", nativeQuery = true)
    Optional<userEntity> findUser(String email, String password);
    
    @Query(value = "SELECT * FROM users WHERE users.email = ?1", nativeQuery = true)
    userEntity findUserByEmail(String email);
    
}