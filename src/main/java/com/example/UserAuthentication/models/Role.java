package com.example.UserAuthentication.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SecondaryRow;

@Getter
@Setter
@Entity(name = "role")
@NoArgsConstructor
public class Role extends BaseModel{

    private String roleName;

    public Role(String roleName){
        this.roleName = roleName;
    }
}
