package softuni.exam.instagraphlite.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "posts")
public class Post extends BaseEntity{

    @Column(nullable = false)
    private String caption;
    @ManyToOne(optional = false)
    private User user;
    @ManyToOne(optional = false)
    private Picture picture;

    @Override
    public String toString() {
        return String.format(  "==Post Details:\n" +
                "----Caption: %s\n" +
                "----Picture Size: %.2f\n",this.caption, this.getPicture().getSize());
    }
}
