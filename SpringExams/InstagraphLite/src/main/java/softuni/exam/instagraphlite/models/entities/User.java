package softuni.exam.instagraphlite.models.entities;

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
@Table(name = "users")
public class User extends BaseEntity{

    @Column(name = "username", nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @ManyToOne(optional = false)
    private Picture profilePicture;

    @OneToMany(mappedBy = "user")
    private Set<Post> posts;

    @Override
    public String toString() {
        return String.format(
                "User: %s\n" +
                "Post count: %d\n", this.username,this.posts.size());
    }
}
