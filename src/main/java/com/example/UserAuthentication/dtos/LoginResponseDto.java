package com.example.UserAuthentication.dtos;

import com.example.UserAuthentication.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDto {
    private User user;
    private ResponseStatus responseStatus;
}
