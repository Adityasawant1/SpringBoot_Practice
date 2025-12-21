package com.spring.project.SpringSecurity.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SignUpDTO {

    String email;
    String password;
    String name;

}
