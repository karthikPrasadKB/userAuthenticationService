package com.example.UserAuthentication.controllers;

import com.example.UserAuthentication.dtos.*;
import com.example.UserAuthentication.dtos.ResponseStatus;
import com.example.UserAuthentication.exceptions.EmailAlreadyExists;
import com.example.UserAuthentication.exceptions.IncorrectPasswordException;
import com.example.UserAuthentication.exceptions.UserNotFoundException;
import com.example.UserAuthentication.models.Token;
import com.example.UserAuthentication.models.User;
import com.example.UserAuthentication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/login")
    public LoginResponseDto userLogin(@RequestBody LoginRequestDto loginRequestDto) throws
                                        UserNotFoundException, IncorrectPasswordException {
        LoginResponseDto loginResponseDto = new LoginResponseDto();
        try{
            Token token = this.userService.userLogin(loginRequestDto.getEmail(), loginRequestDto.getPassword());
            loginResponseDto.setToken(token);
            loginResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
        }catch (UserNotFoundException e){
            loginResponseDto.setResponseStatus(ResponseStatus.USER_NOT_FOUND);
        }catch (IncorrectPasswordException e){
            loginResponseDto.setResponseStatus(ResponseStatus.INCORRECT_PASSWORD);
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
        }catch (EmailAlreadyExists e){
            signupResponseDto.setResponseStatus(ResponseStatus.EMAIL_ALREADY_EXISTS);
        }
        return signupResponseDto;
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> userLogout(@RequestParam("token") String token){
        //Yet to implement
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/validate/{token}")
    public Boolean validateToken(@PathVariable("token") String token){
        //Yet to implement
        return false;
    }
}
