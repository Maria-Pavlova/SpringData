package p01_GringottsDatabase_Updated;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Main01 {
    public static void main(String[] args) {

        // FILL YOUR PU_NAME !
        EntityManager em = Persistence.createEntityManagerFactory("PU_Name")
                .createEntityManager();
        em.getTransaction().begin();



        em.getTransaction().commit();
        em.close();
    }
}
