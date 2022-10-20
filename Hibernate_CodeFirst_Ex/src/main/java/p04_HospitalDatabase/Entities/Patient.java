package p04_HospitalDatabase.Entities;

import javax.persistence.*;
import javax.swing.*;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name", nullable = false)
    private  String firstName;

    @Column(name = "last_name", nullable = false)
    private  String lastName;

    @Column(nullable = false)
    private  String address;

    @Column(nullable = false, unique = true)
    private  String email;

    @Column(name = "date_of_birth", nullable = false, updatable = false)
    private LocalDate dateOfBirth;

    @Column(columnDefinition = "BLOB")
    private ImageIcon picture;

    @Column(name = "has_medical_insurance", nullable = false)
    private boolean hasMedicalInsurance;

    @OneToMany(targetEntity = Visitations.class, mappedBy = "patient")
    private Set<Visitations> visitations;

    @OneToMany(targetEntity = Diagnoses.class, mappedBy = "patient")
    private Set<Diagnoses> diagnoses;

    @ManyToMany
    @JoinTable(name = "patients_medicaments",
            joinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "medicament_id", referencedColumnName = "id"))
    private Set<PrescribedMedicaments> prescribedMedicaments;


    public Patient() {
    }

    public Patient(String firstName, String lastName, String address, String email,
                   LocalDate dateOfBirth, ImageIcon picture, boolean hasMedicalInsurance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.picture = picture;
        this.hasMedicalInsurance = hasMedicalInsurance;
        this.visitations = new HashSet<>();
        this.diagnoses = new HashSet<>();
        this.prescribedMedicaments = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public ImageIcon getPicture() {
        return picture;
    }

    public void setPicture(ImageIcon picture) {
        this.picture = picture;
    }

    public boolean isHasMedicalInsurance() {
        return hasMedicalInsurance;
    }

    public void setHasMedicalInsurance(boolean hasMedicalInsurance) {
        this.hasMedicalInsurance = hasMedicalInsurance;
    }

    public Set<Visitations> getVisitations() {
        return Collections.unmodifiableSet(visitations);
    }

    public Set<Diagnoses> getDiagnoses() {
        return Collections.unmodifiableSet(diagnoses);
    }

    public void setDiagnoses(Set<Diagnoses> diagnoses) {
        this.diagnoses = diagnoses;
    }

    public Set<PrescribedMedicaments> getPrescribedMedicaments() {
        return Collections.unmodifiableSet(prescribedMedicaments);
    }

    public void setPrescribedMedicaments(Set<PrescribedMedicaments> prescribedMedicaments) {
        this.prescribedMedicaments = prescribedMedicaments;
    }
}
