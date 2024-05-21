package com.example.UserAuthentication.services;

import com.example.UserAuthentication.exceptions.EmailAlreadyExists;
import com.example.UserAuthentication.exceptions.UserNotFoundException;
import com.example.UserAuthentication.models.User;

public interface UserService {
    User userLogin(String email, String password) throws UserNotFoundException;
    User userSignup(String name, String email, String password) throws EmailAlreadyExists;
}
