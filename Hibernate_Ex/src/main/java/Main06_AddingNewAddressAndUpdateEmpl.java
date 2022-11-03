import entities.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class Main06_AddingNewAddressAndUpdateEmpl {
    private static final String DATABASE_NAME = "soft_uni";
    private static final String UPDATE_EMPLOYEE_ADDRESS = "UPDATE Employee e SET e.address = :address " +
                                                          "WHERE e.lastName = :name";

    public static void main(String[] args) {

        EntityManager entityManager = Persistence.createEntityManagerFactory(DATABASE_NAME).createEntityManager();

        entityManager.getTransaction().begin();

        String addressText = "Vitoshka 15";
        Address address = new Address();
        address.setText(addressText);
        entityManager.persist(address);

        String newName = new Scanner(System.in).nextLine();

        int count = entityManager.createQuery(UPDATE_EMPLOYEE_ADDRESS)
                .setParameter("name", newName)
                .setParameter("address", address)
                .executeUpdate();

        if (count > 0){
            entityManager.getTransaction().commit();
        }else {
            entityManager.getTransaction().rollback();
        }
        entityManager.close();
    }
}
