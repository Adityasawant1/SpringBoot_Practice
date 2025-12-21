package com.spring.project.SpringSecurity.services;

import com.spring.project.SpringSecurity.dto.LoginDTO;
import com.spring.project.SpringSecurity.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public String login(LoginDTO loginDTO) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getEmail(),
                        loginDTO.getPassword()
                )
        );

        UserEntity user = (UserEntity) authentication.getPrincipal();

        return jwtService.generateToken(user);
    }
}
