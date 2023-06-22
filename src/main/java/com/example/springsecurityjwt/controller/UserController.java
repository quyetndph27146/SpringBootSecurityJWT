package com.example.springsecurityjwt.controller;

import com.example.springsecurityjwt.dtos.request.LoginRequest;
import com.example.springsecurityjwt.dtos.request.SignupRequest;
import com.example.springsecurityjwt.dtos.response.JwtResponse;
import com.example.springsecurityjwt.dtos.response.MessageResponse;
import com.example.springsecurityjwt.entity.ERole;
import com.example.springsecurityjwt.entity.Roles;
import com.example.springsecurityjwt.entity.Users;
import com.example.springsecurityjwt.jwt.JWTTokenProvider;
import com.example.springsecurityjwt.security.CustomUserDetails;
import com.example.springsecurityjwt.service.RoleService;
import com.example.springsecurityjwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTTokenProvider tokenProvider;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder encoder;


    @GetMapping("/signin")
    public String pageLogin(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        System.out.println(11111);
        System.out.println(1111111);
        return "signin";
    }

    @PostMapping("/signin-success")
    public String loginUser(@ModelAttribute(name = "loginRequest") LoginRequest loginRequest) {
        System.out.println(1111111);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        String jwt = tokenProvider.genereteToken(customUserDetails);
        List<String> listRoles = customUserDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        System.out.println(1222222);
        return "trangchu";
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/trang-chu")
    public String trangchu() {
        return "trangchu";
    }
}
