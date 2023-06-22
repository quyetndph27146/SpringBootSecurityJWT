package com.example.springsecurityjwt.security;

import com.example.springsecurityjwt.entity.RoleUser;
import com.example.springsecurityjwt.entity.Roles;
import com.example.springsecurityjwt.entity.Users;
import com.example.springsecurityjwt.jwt.JWTTokenProvider;
import com.example.springsecurityjwt.repository.RoleRepository;
import com.example.springsecurityjwt.repository.RoleUserRepository;
import com.example.springsecurityjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleUserRepository roleUserRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userRepository.findByUserName(username);
        if (users == null) {
            throw new UsernameNotFoundException("User not found");
        }
        List<RoleUser> list = roleUserRepository.findAllByUserId(users.getUserId());
        List<Integer> integers = list.stream().map(RoleUser::getRoleId).collect(Collectors.toList());
        List<Roles> roles = roleRepository.findByRoleIds(integers);
        List<String> roleStr = roles.stream().map(Roles::getRoleName).collect(Collectors.toList());

        return CustomUserDetails.mapUserToUserDetail(users, roleStr);
    }
}
