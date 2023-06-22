package com.example.springsecurityjwt.repository;

import com.example.springsecurityjwt.entity.RoleUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleUserRepository extends JpaRepository<RoleUser, Integer> {
    List<RoleUser> findAllByUserId(Integer userId);
}
