package com.example.springsecurityjwt.dtos.request;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

@Data
public class SignupRequest {

    private String userName;
    private String password;
    private String email;
    private String phone;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date created = new Date();
    private boolean userStatus = true;
    private Set<String> listRoles;
}
