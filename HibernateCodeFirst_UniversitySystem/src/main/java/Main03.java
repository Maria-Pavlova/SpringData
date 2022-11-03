import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main03 {
    public static void main(String[] args) {

        EntityManagerFactory f = Persistence.createEntityManagerFactory("university_system");
        EntityManager em = f.createEntityManager();
        em.getTransaction().begin();



        em.getTransaction().commit();
        em.close();
    }
}
