package com.example.UserAuthentication.services;

import com.example.UserAuthentication.exceptions.EmailAlreadyExists;
import com.example.UserAuthentication.exceptions.UserNotFoundException;
import com.example.UserAuthentication.models.User;
import com.example.UserAuthentication.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User userLogin(String email, String password) throws UserNotFoundException {
        Optional<User> optionalUser = this.userRepository.findByEmail(email);
        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("The entered email does not exist. Please try signing up.");
        }
        return optionalUser.get();
    }

    @Override
    public User userSignup(String name, String email, String password) throws EmailAlreadyExists {
        Optional<User> optionalUser = this.userRepository.findByEmail(email);
        if(optionalUser.isPresent()){
            throw new EmailAlreadyExists("User with email already exists. Please try logging in");
        }
        User newUser = new User();
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPassword(password);
        return this.userRepository.save(newUser);
    }
}
