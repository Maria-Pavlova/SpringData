package p04_HospitalDatabase;
import p04_HospitalDatabase.Entities.Patient;
import p04_HospitalDatabase.Entities.Visitations;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class Main04 {
    public static void main(String[] args) {


        EntityManagerFactory f = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager em = f.createEntityManager();
        em.getTransaction().begin();


        Patient patient = new Patient("p1","p1last", "address", "email", LocalDate.now(), null, true );
        Visitations visitation1 = new Visitations(LocalDate.now(), "comment", patient);
        em.persist(patient);
        em.persist(visitation1);

        em.getTransaction().commit();
        em.close();
    }
}
