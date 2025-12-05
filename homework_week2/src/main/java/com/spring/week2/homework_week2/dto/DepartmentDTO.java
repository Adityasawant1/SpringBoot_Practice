package com.spring.week2.homework_week2.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO {
    private Long id;
    private String title;
    private boolean isActive;
    private LocalDateTime createdAt;

}
