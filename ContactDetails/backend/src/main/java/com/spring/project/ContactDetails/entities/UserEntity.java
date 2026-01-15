    package com.spring.project.ContactDetails.entities;


    import jakarta.persistence.*;
    import lombok.AccessLevel;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;
    import lombok.experimental.FieldDefaults;

    @Data
    @AllArgsConstructor @NoArgsConstructor
    @Entity
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public class UserEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id;

        @Column(unique = true,nullable = false)
        String email;

        String password;

        String name;


    }
