package com.example.football.models.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "teams")
public class Team extends BaseEntity{

    @Column(unique = true, nullable = false)
    private String name;

    @Column(name = "stadium_name",nullable = false)
    private String stadiumName;

    @Column(name = "fan_base",nullable = false)
    private int fanBase;

    @Column(columnDefinition = "TEXT",nullable = false)
    private String history;

    @ManyToOne
    private Town town;

}
