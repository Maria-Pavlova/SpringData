import entities.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main07_AddressesWithEmployeeCount {
    public static void main(String[] args) {

        EntityManagerFactory f = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = f.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createQuery("SELECT a FROM Address a " +
                "ORDER BY a.employees.size DESC", Address.class)
                .setMaxResults(10)
                .getResultStream()
                .forEach(System.out::println);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
