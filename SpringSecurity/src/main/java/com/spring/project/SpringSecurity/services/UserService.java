package com.spring.project.SpringSecurity.services;

import com.spring.project.SpringSecurity.dto.SignUpDTO;
import com.spring.project.SpringSecurity.dto.UserDTO;
import com.spring.project.SpringSecurity.entities.UserEntity;
import com.spring.project.SpringSecurity.entities.enums.Role;
import com.spring.project.SpringSecurity.exception.ResourceNotfoundException;
import com.spring.project.SpringSecurity.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(()->new ResourceNotfoundException("User with email "+ username +" not found"));
    }

    public UserDTO signUp(SignUpDTO signUpDTO) {


        Optional<UserEntity> userEntity = userRepository.findByEmail(signUpDTO.getEmail());

        if(userEntity.isPresent())
        {
            throw new BadCredentialsException("User already existed in database"+signUpDTO.getEmail());
        }

        UserEntity user = modelMapper.map(signUpDTO,UserEntity.class);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (signUpDTO.getRoles() == null || signUpDTO.getRoles().isEmpty()) {
            user.setRoles(Set.of(Role.USER));
        } else {
            user.setRoles(signUpDTO.getRoles());
        }
        return modelMapper.map(userRepository.save(user),UserDTO.class);

    }

    public UserEntity getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(()->new ResourceNotfoundException("User not exist with userid :"+userId));
    }
}
