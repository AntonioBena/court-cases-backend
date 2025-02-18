package com.interview.court.cases.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.interview.court.cases.model.AuditingModel;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
//TODO make optimiization
@EqualsAndHashCode(callSuper = false)
@Table(name = "user_role")
public class Role extends AuditingModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;
    @Column(unique = true)
    private String name;

    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private List<UserEntity> users;
}
