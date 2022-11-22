package com.example.football.models.entity;


import com.example.football.models.enums.Position;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "players")
public class Player extends BaseEntity{

    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name",nullable = false)
    private String lastName;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(name = "birth_date",nullable = false)
    private LocalDate birthDate;
    @Enumerated(EnumType.STRING)
    private Position position;
    @ManyToOne
    private Town town;
    @ManyToOne
    private Team team;
    @ManyToOne
    private Stat stat;

    @Override
    public String toString() {
        return String.format("Player - %s %s%n\tPosition - %s%n\tTeam - %s%n\tStadium - %s",
                firstName,lastName,position,team.getName(),team.getStadiumName());




    }
}
