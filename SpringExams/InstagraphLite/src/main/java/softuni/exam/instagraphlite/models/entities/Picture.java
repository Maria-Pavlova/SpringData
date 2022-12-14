package softuni.exam.instagraphlite.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pictures")
public class Picture extends BaseEntity{

    @Column(nullable = false, unique = true)
    private String path;
    @Column(nullable = false)
    private double size;

    @Override
    public String toString() {
        return String.format("%.2f - %s\n", size, path);
    }
}
