package com.spring.project.SpringSecurity.controllers;

import com.spring.project.SpringSecurity.dto.LoginDTO;
import com.spring.project.SpringSecurity.dto.SignUpDTO;
import com.spring.project.SpringSecurity.dto.UserDTO;
import com.spring.project.SpringSecurity.services.AuthService;
import com.spring.project.SpringSecurity.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/auth")
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping(path = "/signup")
    ResponseEntity<UserDTO>signUp(@RequestBody SignUpDTO signUpDTO)
    {
        UserDTO userDTO1 = userService.signUp(signUpDTO);
        return ResponseEntity.ok(userDTO1);
    }

    @GetMapping(path = "/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO, HttpServletRequest request, HttpServletResponse response)
    {
        String token = authService.login(loginDTO);

        Cookie cookie = new Cookie("token",token);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        
        return ResponseEntity.ok(token);
    }



}
