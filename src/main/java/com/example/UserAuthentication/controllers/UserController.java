package com.example.UserAuthentication.controllers;

import com.example.UserAuthentication.dtos.LoginRequestDto;
import com.example.UserAuthentication.dtos.LoginResponseDto;
import com.example.UserAuthentication.dtos.SignupRequestDto;
import com.example.UserAuthentication.dtos.SignupResponseDto;
import com.example.UserAuthentication.models.User;
import com.example.UserAuthentication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/login")
    public LoginResponseDto userLogin(LoginRequestDto loginRequestDto){
        return null;
    }

    @PostMapping("/signup")
    public SignupResponseDto userSignup(SignupRequestDto signupRequestDto){
        return null;
    }


}
