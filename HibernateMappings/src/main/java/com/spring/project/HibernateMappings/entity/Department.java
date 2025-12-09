package com.spring.project.HibernateMappings.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true,length = 50)
    private String name;

    @OneToOne
    @JoinColumn(nullable = false)
    private Doctor headDoctor;

    @ManyToOne
    private Set<Doctor> doctorSet = new HashSet<>();
}
