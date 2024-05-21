package com.example.UserAuthentication.exceptions;

public class EmailAlreadyExists extends Exception {
    public EmailAlreadyExists(String message){
        super(message);
    }
}
