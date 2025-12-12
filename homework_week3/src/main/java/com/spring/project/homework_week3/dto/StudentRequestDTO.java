package com.spring.project.homework_week3.dto;

import lombok.Data;
import java.util.List;

@Data
public class StudentRequestDTO {

    private String name;

    // Relations handled by ID only
    private List<Long> professorIds;
    private List<Long> subjectIds;

    private Integer admissionFees;    // For admission record creation
}
