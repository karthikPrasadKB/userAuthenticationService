package com.example.UserAuthentication.services;

import com.example.UserAuthentication.dtos.SendEmailDTO;
import com.example.UserAuthentication.exceptions.*;
import com.example.UserAuthentication.models.Role;
import com.example.UserAuthentication.models.Token;
import com.example.UserAuthentication.models.User;
import com.example.UserAuthentication.repositories.TokenRepository;
import com.example.UserAuthentication.repositories.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private TokenRepository tokenRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, TokenRepository tokenRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public Token userLogin(String email, String password) throws UserNotFoundException, IncorrectPasswordException {
        Optional<User> optionalUser = this.userRepository.findByEmail(email);
        //Validate email
        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("The entered email does not exist. Please try signing up.");
        }
        User currentUser = optionalUser.get();
        //Validate password
        if(!this.bCryptPasswordEncoder.matches(password, currentUser.getPassword())){
            throw new IncorrectPasswordException("Incorrect password. Please try again");
        }
        Token currentUserToken = new Token();
        currentUserToken.setUser(currentUser);
        currentUserToken.setTokenValue(UUID.randomUUID().toString());
        currentUserToken.setExpiryDate(getThirtyDaysLaterDate());
        currentUserToken.setExpired(false);
        return this.tokenRepository.save(currentUserToken);
    }

    @Override
    public User userSignup(String name, String email, String password) throws EmailAlreadyExists, JsonProcessingException {
        System.out.println("Services : userSignup");
        Optional<User> optionalUser = this.userRepository.findByEmail(email);
        //Validate if email already exists
        if(optionalUser.isPresent()){
            throw new EmailAlreadyExists("User with email already exists. Please try logging in");
        }
        User newUser = new User();
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPassword(this.bCryptPasswordEncoder.encode(password));
        //send() expects string. But we need to send a DTO which is an object. Hence using objectmapper
        kafkaTemplate.send("sendEmail", this.objectMapper.writeValueAsString(this.createMesageFormat(newUser)));
        return this.userRepository.save(newUser);
    }

    private SendEmailDTO createMesageFormat(User newUser) {
        SendEmailDTO sendEmailDTO = new SendEmailDTO();
        sendEmailDTO.setTo(newUser.getEmail());
        sendEmailDTO.setFrom("clientEmail@email.com");
        sendEmailDTO.setMessage("Your signup is successful. Have a great day");
        sendEmailDTO.setSubject("Signup successful");
        return sendEmailDTO;
    }

    @Override
    public void logout(String tokenValue) throws InvalidTokenException, TokenExpiredException {
        this.validateToken(tokenValue);
    }

    private Date getThirtyDaysLaterDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, 30);
        return calendar.getTime();
    }

    public Boolean validateToken(String tokenId) throws InvalidTokenException, TokenExpiredException {
        Optional<Token> optionalToken = this.tokenRepository.findByTokenValue(tokenId);
        if (optionalToken.isEmpty()) {
            throw new InvalidTokenException("Token is invalid");
        }
        Token token = optionalToken.get();
        if(token.getExpiryDate().before(new Date())){
            token.setExpired(true);
            this.tokenRepository.save(token);
            throw new TokenExpiredException("Token has expired");
        }
        return true;
    }
}
