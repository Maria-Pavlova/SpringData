package p03_UniversitySystem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main03 {
    public static void main(String[] args) {


        EntityManagerFactory f = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager em = f.createEntityManager();
        em.getTransaction().begin();



        em.getTransaction().commit();
        em.close();
    }
}
