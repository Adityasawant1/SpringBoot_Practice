package com.spring.project.homework_week3.repository;

import com.spring.project.homework_week3.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentEntity,Long> {
}
