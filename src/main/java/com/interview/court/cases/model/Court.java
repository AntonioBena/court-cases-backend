package com.interview.court.cases.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity //TODO make optimization
@EqualsAndHashCode(callSuper = false)
@Table(name = "court")
public class Court extends AuditingModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "court_id", nullable = false, updatable = false)
    private Long id;
    @Column(name = "name")
    private String courtName;
    @Column(name = "address")
    private String courtAddress; //TODO another table
}