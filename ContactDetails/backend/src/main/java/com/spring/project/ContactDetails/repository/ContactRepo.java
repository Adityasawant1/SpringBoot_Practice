package com.spring.project.ContactDetails.repository;

import com.spring.project.ContactDetails.entities.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactRepo extends JpaRepository<ContactEntity,String> {
    @Override
    Optional<ContactEntity> findById(String s);
}
