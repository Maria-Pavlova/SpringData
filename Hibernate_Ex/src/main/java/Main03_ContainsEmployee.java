

import javax.persistence.*;
import java.util.Scanner;

public class Main03_ContainsEmployee {
    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();

        Scanner scanner = new Scanner(System.in);
        String[] name = scanner.nextLine().split("\\s+");


        Long employeeCount =
                entityManager.createQuery("SELECT COUNT(e) FROM Employee e" +
                                        " WHERE e.firstName = : first_name " +
                                        "AND e.lastName = :last_name",
                                Long.class)
                        .setParameter("first_name", name[0])
                        .setParameter("last_name", name[1])
                        .getSingleResult();

        if (employeeCount > 0) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
