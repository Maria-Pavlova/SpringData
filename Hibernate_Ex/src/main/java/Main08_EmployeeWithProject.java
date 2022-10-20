
import entities.Employee;
import entities.Project;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.*;


public class Main08_EmployeeWithProject {
    public static void main(String[] args) {


        EntityManagerFactory f = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = f.createEntityManager();
        entityManager.getTransaction().begin();

        Scanner scanner = new Scanner(System.in);
        int input = Integer.parseInt(scanner.nextLine());

        Employee employee = entityManager.createQuery("SELECT e FROM Employee e " +
                        "WHERE e.id = : employee_id", Employee.class)
                .setParameter("employee_id", input)
                .getSingleResult();

        List<String> projectName = new ArrayList<>();
        Set<Project> projects = employee.getProjects();
        for (Project project : projects) {
            projectName.add(project.getName());
        }
        Collections.sort(projectName);

        System.out.printf("%s %s - %s%n", employee.getFirstName(), employee.getLastName(), employee.getJobTitle());
        projectName.forEach(System.out::println);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
