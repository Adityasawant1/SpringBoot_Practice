package com.spring.project.homework_week3.dto;

import lombok.Data;
import java.util.List;

@Data
public class StudentDTO {

    // ---------- Response Fields ----------
    private Long id;                       // Returned after saving
    private List<String> professorNames;   // For output
    private List<String> subjectNames;     // For output

    // ---------- Request Fields ----------
    private String name;                   // Input by user
    private List<Long> professorIds;       // Input: IDs only
    private List<Long> subjectIds;         // Input: IDs only
    private Integer admissionFees;         // Input + output
}
