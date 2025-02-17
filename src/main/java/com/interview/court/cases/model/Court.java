package com.interview.court.cases.model;

import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Data
@Table(name = "court")
public class Court {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "court_id")
    private Long id;
    @Column(name = "name")
    private String courtName;
    @Column(name = "address")
    private String courtAddress; //TODO another table
}