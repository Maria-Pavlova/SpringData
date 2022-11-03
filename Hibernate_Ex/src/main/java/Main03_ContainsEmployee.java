

import javax.persistence.*;
import java.util.Scanner;

public class Main03_ContainsEmployee {
    private static final String DATABASE_NAME = "soft_uni";
    private static final String GET_EMPLOYEE_COUNT = "SELECT COUNT(e) FROM Employee e" +
                                                  " WHERE e.firstName = : first_name " +
                                                   "AND e.lastName = :last_name";
    public static void main(String[] args) {

        EntityManager entityManager = Persistence.createEntityManagerFactory(DATABASE_NAME).createEntityManager();

        String[] name =  new Scanner(System.in).nextLine().split("\\s+");

        Long employeeCount =
                entityManager.createQuery(GET_EMPLOYEE_COUNT, Long.class)
                        .setParameter("first_name", name[0])
                        .setParameter("last_name", name[1])
                        .getSingleResult();

        if (employeeCount > 0) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }

        entityManager.close();
    }
}
