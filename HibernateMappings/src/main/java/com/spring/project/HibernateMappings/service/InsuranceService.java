package com.spring.project.HibernateMappings.service;

import com.spring.project.HibernateMappings.entity.Insurance;
import com.spring.project.HibernateMappings.entity.Patient;
import com.spring.project.HibernateMappings.repository.InsuranceRepository;
import com.spring.project.HibernateMappings.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InsuranceService {

    private final InsuranceRepository insuranceRepository;

    private final PatientRepository patientRepository;
    @Transactional
    public Insurance assignInsuranceToPatient(Insurance insurance, Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient Not Found"));

        patient.setInsurance(insurance);
        insurance.setPatient(patient);

        insuranceRepository.save(insurance); // OR patientRepo.save(patient)

        return insurance;
    }

}
