import entities.Employee;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Main05_EmployeesFromDep {
    private static final String PRINT_FORMAT = "%s %s from %s - $%.2f%n";
    private static final String GET_EMPLOYEE_BY_DEPARTMENT = "SELECT e FROM Employee e " +
                                                            "WHERE e.department.name = :name " +
                                                            "ORDER BY e.salary ASC, e.id ASC";
    public static void main(String[] args) {

        EntityManager entityManager = Persistence.createEntityManagerFactory("soft_uni")
                .createEntityManager();

        String department = "Research and Development";
        entityManager
                .createQuery(GET_EMPLOYEE_BY_DEPARTMENT, Employee.class)
                .setParameter("name", department)
                .getResultStream()
                .forEach(e -> System.out.printf(PRINT_FORMAT,
                            e.getFirstName(), e.getLastName(), e.getDepartment().getName(), e.getSalary()));

        entityManager.close();

    }
}
