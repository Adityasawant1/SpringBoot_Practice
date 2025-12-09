package com.spring.project.HibernateMappings.repository;

import com.spring.project.HibernateMappings.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}