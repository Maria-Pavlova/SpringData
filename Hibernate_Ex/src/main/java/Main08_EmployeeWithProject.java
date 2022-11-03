
import entities.Employee;
import entities.Project;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.*;
import java.util.stream.Collectors;


public class Main08_EmployeeWithProject {
    private static final String DATABASE_NAME = "soft_uni";
    private static final String GET_EMPLOYEE_BY_ID = "SELECT e FROM Employee e WHERE e.id = : employee_id";

    public static void main(String[] args) {


        EntityManager entityManager = Persistence.createEntityManagerFactory(DATABASE_NAME)
                .createEntityManager();

        int input = new Scanner(System.in).nextInt();

        Employee employee = entityManager.createQuery(GET_EMPLOYEE_BY_ID, Employee.class)
                .setParameter("employee_id", input)
                .getSingleResult();

        System.out.printf("%s %s - %s%n\t%s",
                employee.getFirstName(),
                employee.getLastName(),
                employee.getJobTitle(),
                employee.getProjects()
                        .stream()
                        .sorted(Comparator.comparing(Project::getName))
                        .map(Project::getName)
                        .collect(Collectors.joining("\n\t")));

        entityManager.close();
    }
}
