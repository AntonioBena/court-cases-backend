package com.interview.court.cases.model.court;

import com.interview.court.cases.model.AuditingModel;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "court")
public class Court extends AuditingModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "court_id", nullable = false, updatable = false)
    private Long id;
    @Column(name = "name")
    private String courtName;
    @Column(name = "address")
    private String courtAddress; //might be another table

    @Override
    public String toString() {
        return "Court{" +
                "id=" + id +
                ", courtName='" + courtName + '\'' +
                ", courtAddress='" + courtAddress + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Court court = (Court) o;
        return Objects.equals(id, court.id) && Objects.equals(courtName, court.courtName) && Objects.equals(courtAddress, court.courtAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courtName, courtAddress);
    }
}