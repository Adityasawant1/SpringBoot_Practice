package com.spring.project.ContactDetails.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UuidGenerator;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;

@Entity
@Data @Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@FieldDefaults(level=AccessLevel.PRIVATE)
@JsonInclude(NON_DEFAULT)
@Table(name = "contacts")
public class ContactEntity {

    @Id
    @UuidGenerator
    @Column(name = "id" ,unique = true,updatable = false)
    String id;

    String name;
    String email;
    String title;
    String phone;
    String address;
    String status;
    String photoUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    UserEntity user;

}
