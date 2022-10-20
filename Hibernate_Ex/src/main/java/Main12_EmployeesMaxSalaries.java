import entities.Department;
import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class Main12_EmployeesMaxSalaries {
    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();


        List resultList = entityManager.createNativeQuery("SELECT d.name, MAX(salary) AS 'max' FROM employees AS e " +
                        "JOIN departments AS d ON e.department_id = d.department_id " +
                        "GROUP BY d.name " +
                        "HAVING `max` NOT BETWEEN 30000 AND 70000;")

                .getResultList();


//                .forEach(d -> System.out.printf("%s%n",
//                        ));

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
