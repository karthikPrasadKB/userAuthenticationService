package com.example.UserAuthentication.security.services;

import com.example.UserAuthentication.models.Role;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;


public class CustomGrantedAuthority implements GrantedAuthority {

    private String role;

    public CustomGrantedAuthority() {
    }

    public CustomGrantedAuthority(Role role){
        this.role = role.getRoleName();
    }

    @Override
    public String getAuthority() {
        return this.role;
    }
}
