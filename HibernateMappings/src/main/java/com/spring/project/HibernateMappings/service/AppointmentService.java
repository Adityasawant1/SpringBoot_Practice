package com.spring.project.HibernateMappings.service;

import com.spring.project.HibernateMappings.entity.Appointment;
import com.spring.project.HibernateMappings.entity.Doctor;
import com.spring.project.HibernateMappings.entity.Patient;
import com.spring.project.HibernateMappings.repository.AppointmentRepository;
import com.spring.project.HibernateMappings.repository.DoctorRepository;
import com.spring.project.HibernateMappings.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    @Transactional
    public Appointment createANewAppointment(Appointment appointment,Long patientId,Long doctorId)
    {
        Patient patient = patientRepository.findById(patientId).orElseThrow();
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        appointmentRepository.save(appointment);

        return appointment;
    }
}
