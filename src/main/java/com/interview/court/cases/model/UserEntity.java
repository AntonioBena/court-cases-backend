package com.interview.court.cases.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
//TODO make optimiization
@EqualsAndHashCode(callSuper = false)
@Table(name = "user_entity")
public class UserEntity extends AuditingModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_entity_id")
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "user_password") //TODO will be hashed
    private String password;
    @Column(name = "user_email")
    private String email;
    @Column(name = "is_user_active")
    private boolean isActive;
}