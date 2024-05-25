package com.example.UserAuthentication.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SecondaryRow;

@Getter
@Setter
@Entity(name = "role")
public class Role extends BaseModel{
    private String roleName;
}
