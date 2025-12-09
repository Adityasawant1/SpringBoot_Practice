package com.spring.project.HibernateMappings;

import com.spring.project.HibernateMappings.entity.Appointment;
import com.spring.project.HibernateMappings.entity.Insurance;
import com.spring.project.HibernateMappings.entity.Patient;
import com.spring.project.HibernateMappings.service.AppointmentService;
import com.spring.project.HibernateMappings.service.InsuranceService;
import com.spring.project.HibernateMappings.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
public class InsuranceTest {

    @Autowired
    private InsuranceService insuranceService;

    @Autowired
    private AppointmentService appointmentService;


    @Autowired
    private PatientService patientService;

    @Test
    public void testAssignInsuranceToPatient()
    {
        Insurance insurance = Insurance.builder()
                .provider("HDFC Ergo")
                .policyNumber("HDFC_23G")
                .validUntil(LocalDate.of(2030,1,1))
                .build();

        var updatedInsurance = insuranceService.assignInsuranceToPatient(insurance,1L);

        System.out.println(updatedInsurance);

        //patientService.deletePatient(1L);

    }

    @Test
    public void createAppointment()
    {
        Appointment appointment = Appointment.builder()
                .appointmentTime(LocalDateTime.of(2025,11,1,14,0,0))
                .reason("Cancer")
                .build();

        var createdAppo=appointmentService.createANewAppointment(appointment,1L,2L);

        System.out.println(createdAppo);

        patientService.deletePatient(1L);


    }
}
