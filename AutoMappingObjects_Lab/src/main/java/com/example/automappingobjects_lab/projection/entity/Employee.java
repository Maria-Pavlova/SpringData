package com.example.automappingobjects_lab.projection.entity;

import lombok.*;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private BigDecimal salary;
    @NonNull
    private LocalDate birthdate;
    @ManyToOne
    @NonNull
    private Address address;
    @ManyToOne(optional = true,fetch = FetchType.EAGER)
    @JoinColumn(name = "manager_id", referencedColumnName = "id")
    private Employee manager;
    @OneToMany(mappedBy = "manager", fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Employee> subordinates = new ArrayList<>();
    private boolean isOnHoliday;


}
