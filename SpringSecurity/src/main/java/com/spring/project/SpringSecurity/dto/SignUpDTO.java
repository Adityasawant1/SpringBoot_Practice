package com.spring.project.SpringSecurity.dto;

import com.spring.project.SpringSecurity.entities.enums.Role;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SignUpDTO {

    String email;
    String password;
    String name;

    Set<Role> roles;

}
