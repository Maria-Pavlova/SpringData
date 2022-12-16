package com.example.workshopnextleveltechnologiesmvc.data.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "projects")
public class Project extends BaseEntity{
    @Column(nullable = false)
    private String name;
    @Column(columnDefinition = "text", nullable = false)
    private String description;
    @Column(name = "is_finished")
    private Boolean isFinished;
    @Column(nullable = false)
    private BigDecimal payment;
    @Column(name = "start_date")
    private LocalDate startDate;
    @ManyToOne
    private Company company;


}
