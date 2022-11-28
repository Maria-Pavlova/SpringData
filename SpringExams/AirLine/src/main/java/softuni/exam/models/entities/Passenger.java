package softuni.exam.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "passengers")
public class Passenger extends BaseEntity{
    @Column(name = "first_name",nullable = false)
    private String firstName;
    @Column(name = "last_name",nullable = false)
    private String lastName;
    @Column
    private int age;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(nullable = false,unique = true)
    private String email;
    @ManyToOne
    @Fetch(FetchMode.JOIN)
    private Town town;
    @OneToMany(mappedBy = "passenger")
    @Fetch(FetchMode.JOIN)
    private Set<Ticket> tickets;

    public int getTicketsSize() {
        return tickets.size();
    }

    @Override
    public String toString() {
        return String.format("Passenger %s  %s\n" +
                "\tEmail - %s\n" +
                "\tPhone - %s\n" +
                "\tNumber of tickets - %d\n",
                firstName, lastName,email,phoneNumber,tickets.size());
    }
}
