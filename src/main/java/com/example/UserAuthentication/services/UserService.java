package com.example.UserAuthentication.services;

import com.example.UserAuthentication.exceptions.*;
import com.example.UserAuthentication.models.Token;
import com.example.UserAuthentication.models.User;

public interface UserService {
    Token userLogin(String email, String password) throws UserNotFoundException, IncorrectPasswordException;
    User userSignup(String name, String email, String password) throws EmailAlreadyExists;
    void logout(String token) throws InvalidTokenException, TokenExpiredException;
}
