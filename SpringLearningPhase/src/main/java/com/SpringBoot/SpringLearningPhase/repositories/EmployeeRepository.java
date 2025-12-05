package com.SpringBoot.SpringLearningPhase.repositories;


import com.SpringBoot.SpringLearningPhase.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
}
