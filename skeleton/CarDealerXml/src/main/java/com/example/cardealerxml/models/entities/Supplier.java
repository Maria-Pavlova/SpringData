package com.example.cardealerxml.models.entities;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "suppliers")
public class Supplier extends BaseEntity{
    private String name;
    private boolean isImporter;
    @OneToMany(mappedBy = "supplier")
    @Fetch(FetchMode.JOIN)
    private Set<Part> parts;
}
