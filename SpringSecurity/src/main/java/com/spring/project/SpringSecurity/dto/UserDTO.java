package com.spring.project.SpringSecurity.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {

    private Long id;
    private String email;
    private String name;
}
