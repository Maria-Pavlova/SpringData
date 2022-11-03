import entities.Employee;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.Scanner;

public class Main11_FindEmployeeByFirstName {
    private static final String DATABASE_NAME = "soft_uni";
    private static final String GET_EMPLOYEE_BY_NAME = "SELECT e FROM Employee e WHERE e.firstName LIKE :searched";

    public static void main(String[] args) {

        EntityManager entityManager = Persistence.createEntityManagerFactory(DATABASE_NAME)
                .createEntityManager();

        String input = new Scanner(System.in).nextLine();

        entityManager.createQuery(GET_EMPLOYEE_BY_NAME, Employee.class)
                .setParameter("searched", input + "%")
                .getResultStream()
                .forEach(e -> System.out.printf("%s %s - %s - ($%.2f)%n",
                         e.getFirstName(), e.getLastName(), e.getJobTitle(), e.getSalary()));

        entityManager.close();
    }
}
