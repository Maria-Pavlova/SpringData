package entities;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "companies")
public class Companies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "companies", targetEntity = Plane.class,
            fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Plane> planes;


    public Companies(String name) {
        this.name = name;
        this.planes = new HashSet<>();

    }

    public Companies() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Plane> getPlanes() {
        return Collections.unmodifiableSet(this.planes);
    }

    public void addPlane(Plane plane){
        planes.add(plane);
    }
}
