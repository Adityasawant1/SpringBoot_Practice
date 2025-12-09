package com.spring.project.HibernateMappings.repository;

import com.spring.project.HibernateMappings.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}