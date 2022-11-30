package hiberspring.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
public class Employee extends BaseEntity{

    @Column(name = "first_name",nullable = false)
    private String firstName;
    @Column(name = "last_name",nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String position;

    @ManyToOne
    private EmployeeCard card;

    @ManyToOne
    @Fetch(FetchMode.JOIN)
    private Branch branch;

    @Override
    public String toString() {
        return String.format("Name: %s %s\n" +
                "Position: %s\n" +
                "Card Number: %s\n",
                this.firstName, this.lastName, this.position, this.getCard().getNumber());
    }
}
