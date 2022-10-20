import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class Main02_ChangeCasing {
    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        Query fromTown = entityManager.createQuery("SELECT t FROM Town t", Town.class);
        List<Town> resultList = fromTown.getResultList();
        for (Town town : resultList) {
            String name = town.getName();
            if (name.length() <= 5){
                town.setName(name.toUpperCase());
                entityManager.persist(town);
            }
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
