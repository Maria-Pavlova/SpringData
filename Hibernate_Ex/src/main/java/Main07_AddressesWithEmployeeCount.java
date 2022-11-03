import entities.Address;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Main07_AddressesWithEmployeeCount {
    private static final String DATABASE_NAME = "soft_uni";
    private static final String GET_ADDRESS_WITH_EMPLOYEE_COUNT = "SELECT a FROM Address a ORDER BY a.employees.size DESC";

    public static void main(String[] args) {

        EntityManager entityManager = Persistence.createEntityManagerFactory(DATABASE_NAME)
                .createEntityManager();

        entityManager.createQuery(GET_ADDRESS_WITH_EMPLOYEE_COUNT, Address.class)
                .setMaxResults(10)
                .getResultList()
                .forEach(System.out::println);

        entityManager.close();
    }
}
