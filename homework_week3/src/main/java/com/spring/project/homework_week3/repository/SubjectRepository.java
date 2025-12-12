package com.spring.project.homework_week3.repository;

import com.spring.project.homework_week3.entities.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<SubjectEntity, Long> {
}