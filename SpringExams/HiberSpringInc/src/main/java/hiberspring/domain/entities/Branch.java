package hiberspring.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "branches")
public class Branch extends BaseEntity{
    @Column(nullable = false)
    private String name;
    @ManyToOne
    private Town town;
    @OneToMany(mappedBy = "branch")
    private Set<Product> products;

}
