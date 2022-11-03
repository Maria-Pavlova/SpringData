
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Main12_EmployeesMaxSalaries {
    private static final String DATABASE_NAME = "soft_uni";
    private static final String GET_EMPLOYEE_MAX_SALARIES = "SELECT e.department.name, MAX(e.salary) FROM Employee e " +
            "GROUP BY e.department.name " +
            "HAVING MAX(e.salary) NOT BETWEEN 30000 AND 70000";

    public static void main(String[] args) {

        EntityManager entityManager = Persistence.createEntityManagerFactory(DATABASE_NAME)
                .createEntityManager();

        entityManager.createQuery(GET_EMPLOYEE_MAX_SALARIES, Object[].class)
                .getResultList()
                .forEach(o -> System.out.println(o[0] + " " + o[1]));

        entityManager.close();
    }
}
