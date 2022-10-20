import entities.Address;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class Main13_RemoveTowns {
    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        Town town = entityManager
                .createQuery("SELECT t FROM Town t" +
                        " WHERE t.name = :name", Town.class)
                .setParameter("name", input)
                .getSingleResult();


        List<Address> addresses = entityManager
                .createQuery("SELECT a FROM Address a " +
                        "WHERE a.town.id = :p_id", Address.class)
                .setParameter("p_id", town.getId())
                .getResultList();
        int count = addresses.size();

        addresses.forEach(entityManager::remove);

        entityManager.remove(town);

        String format = String.format("%d %s in %s deleted",
                count,
                count == 1 ? "address" : "addresses",
                input);
        System.out.println(format);


        entityManager.getTransaction().commit();
        entityManager.close();
    }


}
