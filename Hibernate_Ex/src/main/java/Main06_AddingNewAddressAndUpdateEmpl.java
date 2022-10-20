import entities.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class Main06_AddingNewAddressAndUpdateEmpl {
    public static void main(String[] args) {

        EntityManagerFactory f = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = f.createEntityManager();
        entityManager.getTransaction().begin();

        Scanner scanner = new Scanner(System.in);

        String addressText = "Vitoshka 15";
        Address address = new Address();
        address.setText(addressText);
        entityManager.persist(address);

        String newName = scanner.nextLine();

        entityManager.createQuery("UPDATE Employee e " +
                        "SET e.address = :address " +
                        "WHERE e.lastName = :name")
                .setParameter("name", newName)
                .setParameter("address", address)
                .executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
