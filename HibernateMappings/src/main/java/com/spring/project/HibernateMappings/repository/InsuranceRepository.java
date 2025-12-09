package com.spring.project.HibernateMappings.repository;

import com.spring.project.HibernateMappings.entity.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
}