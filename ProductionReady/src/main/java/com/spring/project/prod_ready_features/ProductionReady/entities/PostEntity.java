package com.spring.project.prod_ready_features.ProductionReady.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


;

@Entity
@Table(name = "posts")
@AllArgsConstructor
@NoArgsConstructor
@Data

public class PostEntity extends AuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;




}
