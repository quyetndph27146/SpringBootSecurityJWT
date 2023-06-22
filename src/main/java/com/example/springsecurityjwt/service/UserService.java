package com.example.springsecurityjwt.service;

import com.example.springsecurityjwt.entity.Users;

public interface UserService {
    Users findByUserName(String userName);

    boolean existsByUserName(String userName);

    boolean existsByEmail(String email);

    <S extends Users> S save(S entity);
}
