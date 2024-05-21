package com.example.UserAuthentication.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "user")
public class User extends BaseModel{
    private String name;
    private String email;
    private String password;
}
