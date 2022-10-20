package p04_HospitalDatabase.Entities;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "prescribed_medicaments")
public class PrescribedMedicaments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(targetEntity = Patient.class, mappedBy = "prescribedMedicaments")
    private Set<Patient> patients;

    public PrescribedMedicaments(String name) {
        this.name = name;
        this.patients = new HashSet<>();
    }

    public PrescribedMedicaments() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<Patient> getPatients() {
        return Collections.unmodifiableSet(patients);
    }

}
