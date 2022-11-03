import entities.Address;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class Main13_RemoveTowns {
    private static final String DATABASE_NAME = "soft_uni";
    private static final String GET_TOWN_BY_NAME = "SELECT t FROM Town t WHERE t.name = :name";
    private static final String GET_ADDRESS_BY_ID = "SELECT a FROM Address a WHERE a.town.id = :p_id";

    public static void main(String[] args) {

        EntityManager entityManager = Persistence.createEntityManagerFactory(DATABASE_NAME)
                .createEntityManager();

        String input = new Scanner(System.in).nextLine();

        Town town = entityManager.createQuery(GET_TOWN_BY_NAME, Town.class)
                .setParameter("name", input)
                .getSingleResult();

        List<Address> addresses = entityManager.createQuery(GET_ADDRESS_BY_ID, Address.class)
                .setParameter("p_id", town.getId())
                .getResultList();
        int count = addresses.size();

        addresses.forEach(entityManager::remove);

        entityManager.remove(town);

        String format = String.format("%d %s in %s deleted",
                count,
                count == 1 ? "address" : "addresses",
                town.getName());
        System.out.println(format);


        entityManager.close();
    }


}
