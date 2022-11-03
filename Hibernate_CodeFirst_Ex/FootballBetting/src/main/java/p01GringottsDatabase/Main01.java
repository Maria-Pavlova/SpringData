package p01GringottsDatabase;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main01 {
    public static void main(String[] args) {

        // P06 Football Betting is in this project

        EntityManagerFactory f = Persistence.createEntityManagerFactory("football_betting");
        EntityManager em = f.createEntityManager();
        em.getTransaction().begin();



        em.getTransaction().commit();
        em.close();
    }
}
