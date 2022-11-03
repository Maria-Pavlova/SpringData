import entities.Employee;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;


public class Main04_EmployeesWithSalaryOverThan {
    private static final String DATABASE_NAME = "soft_uni";
    private static final String GET_EMPLOYEE_NAME_BY_SALARY = "SELECT e.firstName FROM Employee e WHERE e.salary > 50000";

    public static void main(String[] args) {

        EntityManager entityManager = Persistence.createEntityManagerFactory(DATABASE_NAME).createEntityManager();

        entityManager
                .createQuery(GET_EMPLOYEE_NAME_BY_SALARY, String.class)
                .getResultList()
                .forEach(System.out::println);

        entityManager.close();
    }
}
