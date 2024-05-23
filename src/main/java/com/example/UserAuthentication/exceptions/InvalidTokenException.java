package com.example.UserAuthentication.exceptions;

public class InvalidTokenException extends Exception{
    public InvalidTokenException(String message){
        super(message);
    }
}
