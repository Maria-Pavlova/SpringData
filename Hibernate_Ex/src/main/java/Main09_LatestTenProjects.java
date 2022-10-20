import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public class Main09_LatestTenProjects {
    public static void main(String[] args) {


        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:yy:ss.S");

        em.createQuery("SELECT p FROM Project p " +
                        "ORDER BY p.startDate DESC ", Project.class)
                .setMaxResults(10)
                .getResultList()
                .stream()
                        .sorted(Comparator.comparing(Project::getName))
                        .forEach(project -> System.out.printf("Project name: %s%n" +
                                        "        Project Description: %s%n" +
                                        "        Project Start Date: %s%n" +
                                        "        Project End Date: %s%n",
                                project.getName(), project.getDescription(),
                                project.getStartDate().format(formatter),
                                project.getEndDate() == null ? "null" : project.getEndDate().format(formatter)
                               ));

//        em.createQuery("SELECT p FROM Project p", Project.class)
//                .getResultList()
//                .stream()
//                .sorted(Comparator.comparing(Project::getStartDate).reversed())
//                .limit(10)
//                .sorted(Comparator.comparing(Project::getName))
//                .forEach(project -> System.out.printf("Project name: %s%n" +
//                                "        Project Description: %s%n" +
//                                "        Project Start Date: %s%n" +
//                                "        Project End Date: %s%n",
//                        project.getName(), project.getDescription(),
//                        project.getStartDate().format(formatter),
//                        project.getEndDate() == null ? "null" : project.getEndDate().format(formatter)
//                ));

        em.getTransaction().commit();
        em.close();
    }
}
