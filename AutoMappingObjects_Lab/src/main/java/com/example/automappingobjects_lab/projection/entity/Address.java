package com.example.automappingobjects_lab.projection.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "addresses")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String details;
    @NonNull
    private String city;
    @NonNull
    private String country;


}
