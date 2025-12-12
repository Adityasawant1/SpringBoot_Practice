package com.spring.project.homework_week3.repository;

import com.spring.project.homework_week3.entities.ProfessorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<ProfessorEntity, Long> {
}