package com.spring.project.ContactDetails.repository;

import com.spring.project.ContactDetails.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,String> {

    UserEntity findByEmail(String email);
}
