package com.example.UserAuthentication.controllers;

import com.example.UserAuthentication.dtos.*;
import com.example.UserAuthentication.exceptions.EmailAlreadyExists;
import com.example.UserAuthentication.exceptions.UserNotFoundException;
import com.example.UserAuthentication.models.User;
import com.example.UserAuthentication.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService){
        this.userService = userService;
    }

    @PostMapping("/login")
    public LoginResponseDto userLogin(@RequestBody LoginRequestDto loginRequestDto) throws UserNotFoundException {
        LoginResponseDto loginResponseDto = new LoginResponseDto();
        try{
            User user = this.userService.userLogin(loginRequestDto.getEmail(), loginRequestDto.getPassword());
            loginResponseDto.setUser(user);
            loginResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }catch (Exception e){
            loginResponseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return loginResponseDto;
    }

    @PostMapping("/signup")
    public SignupResponseDto userSignup(@RequestBody SignupRequestDto signupRequestDto) throws EmailAlreadyExists {
        SignupResponseDto signupResponseDto = new SignupResponseDto();
        try{
            User newUser = this.userService.userSignup(signupRequestDto.getName(), signupRequestDto.getEmail(),
                                                       signupRequestDto.getPassword());
            signupResponseDto.setUser(newUser);
            signupResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }catch (Exception e){
            signupResponseDto.setResponseStatus(ResponseStatus.FAILURE);
        }
        return signupResponseDto;
    }


}
