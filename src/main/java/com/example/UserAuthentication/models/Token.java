package com.example.UserAuthentication.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity(name = "token")
public class Token extends BaseModel{
    private String tokenValue;
    private Date expiryDate;
    private boolean isExpired;
    @ManyToOne
    private User user;
}
