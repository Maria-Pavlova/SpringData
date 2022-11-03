import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.Set;

public class Main10_IncreaseSalaries {
    private static final String DATABASE_NAME = "soft_uni";
    public static final String UPDATE_SALARY = "UPDATE Employee e SET e.salary = e.salary * 1.12 WHERE e.department.id IN :ids";
    public static final String GET_UPDATED = "SELECT e FROM Employee e WHERE e.department.id IN :ids";

    public static void main(String[] args) {

        EntityManager em = Persistence.createEntityManagerFactory(DATABASE_NAME)
                .createEntityManager();

        em.getTransaction().begin();

        int updated = em.createQuery(UPDATE_SALARY)
                .setParameter("ids", Set.of(1, 2, 4, 11))
                .executeUpdate();

        if (updated > 0) {
            em.createQuery(GET_UPDATED, Employee.class)
                    .setParameter("ids", Set.of(1, 2, 4, 11))
                    .getResultList()
                    .forEach(e -> System.out.printf("%s %s ($%.2f)%n", e.getFirstName(), e.getLastName(), e.getSalary()));
        }
        em.getTransaction().commit();
        em.close();
    }
}
