package com.example.UserAuthentication.exceptions;

import com.example.UserAuthentication.models.Token;

public class TokenExpiredException extends Exception{
    public TokenExpiredException(String message){
        super(message);
    }
}
