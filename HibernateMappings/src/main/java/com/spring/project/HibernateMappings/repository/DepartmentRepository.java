package com.spring.project.HibernateMappings.repository;

import com.spring.project.HibernateMappings.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}