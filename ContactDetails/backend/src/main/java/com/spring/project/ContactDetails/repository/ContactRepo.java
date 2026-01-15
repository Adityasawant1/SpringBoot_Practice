package com.spring.project.ContactDetails.repository;

import com.spring.project.ContactDetails.entities.ContactEntity;
import com.spring.project.ContactDetails.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactRepo extends JpaRepository<ContactEntity,String> {
    @Override
    Optional<ContactEntity> findById(String s);

    Optional<Object> findByIdAndUser(String id, UserEntity user);

    Page<ContactEntity> findByUser(UserEntity user, PageRequest name);
}
