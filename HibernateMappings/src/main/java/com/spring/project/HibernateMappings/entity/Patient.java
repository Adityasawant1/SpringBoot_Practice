package com.spring.project.HibernateMappings.entity;
import com.spring.project.HibernateMappings.dto.BloodGroupStats;
import com.spring.project.HibernateMappings.entity.type.BloodGroupType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@ToString
@Getter
@Setter
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate birthDate;

    private String email;

    private String gender;

    @Enumerated(value = EnumType.STRING)
    private BloodGroupType bloodGroup;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="patient_insurance",unique = true)
    private Insurance insurance;     //Owner Side

    @OneToMany(mappedBy = "patient",cascade = CascadeType.ALL)  // Inverse Side
    private List<Appointment> appointmentList = new ArrayList<>();


}