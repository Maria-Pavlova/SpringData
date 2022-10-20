package p05_BillsPaymentSystem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main05 {
    public static void main(String[] args) {


        EntityManagerFactory f = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager em = f.createEntityManager();
        em.getTransaction().begin();



        em.getTransaction().commit();
        em.close();
    }
}
