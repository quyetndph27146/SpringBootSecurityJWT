package com.example.springsecurityjwt.repository;

import com.example.springsecurityjwt.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    Users findByUserName(String userName);

    boolean existsByUserName(String userName);

    boolean existsByEmail(String email);
}
