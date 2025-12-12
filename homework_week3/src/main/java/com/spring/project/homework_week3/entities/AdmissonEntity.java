package com.spring.project.homework_week3.entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdmissonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer fees;
    @OneToOne
    @JoinColumn(name = "student_id",unique = true)
    private StudentEntity student;


}
