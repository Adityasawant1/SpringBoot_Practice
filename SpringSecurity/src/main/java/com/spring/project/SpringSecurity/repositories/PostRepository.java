package com.spring.project.SpringSecurity.repositories;


import com.spring.project.SpringSecurity.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity,Long> {
}
