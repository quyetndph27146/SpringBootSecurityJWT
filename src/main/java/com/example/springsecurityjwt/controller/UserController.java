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
import com.example.springsecurityjwt.security.oauth2.CustomOAuth2User;
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
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
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


    @GetMapping("/login")
    public String pageLogin(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        System.out.println(11111);
        System.out.println(1111111);
        return "login";
    }

    @PostMapping("/signin-success")
    public String loginUser(@ModelAttribute(name = "loginRequest") LoginRequest loginRequest) {
        System.out.println(11111);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        System.out.println(12);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        String jwt = tokenProvider.genereteToken(customUserDetails);
        List<String> listRoles = customUserDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        return "trangchu";
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/trang-chu")
    public String trangchu(OAuth2AuthenticationToken token) {
        System.out.println(token.getPrincipal().getAttribute("email").toString());
        System.out.println(token.getPrincipal().getAttribute("name").toString());
        System.out.println(token.getAuthorities());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication.getPrincipal().toString());
        System.out.println(authentication.getAuthorities());
        return "trangchu";
    }
}
