package com.spring.project.SpringSecurity.controllers;

import com.spring.project.SpringSecurity.dto.LoginDTO;
import com.spring.project.SpringSecurity.dto.LoginResponseDTO;
import com.spring.project.SpringSecurity.dto.SignUpDTO;
import com.spring.project.SpringSecurity.dto.UserDTO;
import com.spring.project.SpringSecurity.services.AuthService;
import com.spring.project.SpringSecurity.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

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
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO loginDTO, HttpServletRequest request, HttpServletResponse response)
    {
        LoginResponseDTO loginResponseDTO = authService.login(loginDTO);

        Cookie cookie = new Cookie("refreshToken",loginResponseDTO.getRefreshToken());
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        
        return ResponseEntity.ok(loginResponseDTO);
    }

    @PostMapping(path = "/refresh")
    public ResponseEntity<LoginResponseDTO> refresh(HttpServletRequest request)
    {
        String refreshToken = Arrays.stream(request.getCookies())
                .filter(cookie -> "refreshToken".equals(cookie.getName()))
                .findFirst()
                .map(Cookie::getValue)
                .orElseThrow(()->new AuthenticationServiceException("Refresh token not found inside the Cookies"));

        LoginResponseDTO loginResponseDTO = authService.refreshToken(refreshToken);
        return ResponseEntity.ok(loginResponseDTO);
    }



}
