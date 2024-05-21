package com.example.UserAuthentication.dtos;

import com.example.UserAuthentication.models.Token;
import com.example.UserAuthentication.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDto {
    private Token token;
    private ResponseStatus responseStatus;
}
