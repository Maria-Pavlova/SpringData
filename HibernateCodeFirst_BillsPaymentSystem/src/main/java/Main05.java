import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main05 {
    public static void main(String[] args) {


        EntityManager em = Persistence.createEntityManagerFactory("bill_payment")
                .createEntityManager();
        em.getTransaction().begin();



        em.getTransaction().commit();
        em.close();
    }
}
