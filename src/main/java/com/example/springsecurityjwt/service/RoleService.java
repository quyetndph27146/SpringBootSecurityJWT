package com.example.springsecurityjwt.service;

import com.example.springsecurityjwt.entity.ERole;
import com.example.springsecurityjwt.entity.Roles;

import java.util.Optional;

public interface RoleService {
    Optional<Roles> findByRoleName(ERole roleName);
}
