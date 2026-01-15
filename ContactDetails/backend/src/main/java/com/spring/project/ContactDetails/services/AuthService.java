package com.spring.project.ContactDetails.services;


import com.spring.project.ContactDetails.dto.LoginDto;
import com.spring.project.ContactDetails.dto.SignUpDto;
import com.spring.project.ContactDetails.dto.UserDto;
import com.spring.project.ContactDetails.entities.UserEntity;
import com.spring.project.ContactDetails.exception.UserAlreadyExistsException;
import com.spring.project.ContactDetails.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;




    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles("USER")
                .build();
    }


    public UserDto register(SignUpDto signUp) {
        if (userRepository.findByEmail(signUp.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("Email already registered");
        }

        UserEntity user = modelMapper.map(signUp,UserEntity.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return modelMapper.map(userRepository.save(user),UserDto.class);

    }


}
