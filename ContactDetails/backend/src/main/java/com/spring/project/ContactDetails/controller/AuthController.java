package com.spring.project.ContactDetails.controller;

import com.spring.project.ContactDetails.dto.LoginDto;
import com.spring.project.ContactDetails.dto.SignUpDto;
import com.spring.project.ContactDetails.dto.UserDto;
import com.spring.project.ContactDetails.entities.UserEntity;
import com.spring.project.ContactDetails.services.AuthService;
import com.spring.project.ContactDetails.services.UserPasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserPasswordService userPasswordService;

    // Register
    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody SignUpDto signUp) {

        return ResponseEntity.ok(authService.register(signUp));
    }

    // Login
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(userPasswordService.login(loginDto));
    }
}
