
import entities.Town;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class Main02_ChangeCasing {
    private static final String DATABASE_NAME = "soft_uni";
    private static final String GET_TOWNS = "SELECT t FROM Town t";

    public static void main(String[] args) {

        EntityManager entityManager = Persistence.createEntityManagerFactory(DATABASE_NAME)
                .createEntityManager();

        entityManager.getTransaction().begin();

        Query fromTown = entityManager.createQuery(GET_TOWNS, Town.class);
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
