package com.spring.project.homework_week3.dto;


import lombok.Data;

import java.util.List;

@Data
public class StudentResponseDTO {

    private Long id;
    private String name;

    // Return readable values, not IDs
    private List<String> professors;   // professor names
    private List<String> subjects;     // subject titles

    private Integer admissionFees;     // Only value needed from AdmissionRecord
}