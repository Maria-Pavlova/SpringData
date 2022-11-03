import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public class Main09_LatestTenProjects {
    private static final String DATABASE_NAME = "soft_uni";
    private static final String GET_PROJECTS = "SELECT p FROM Project p ORDER BY p.startDate DESC ";
    private static final String FORMAT = "yyyy-MM-dd HH:yy:ss.S";
    private static final String PRINT_FORMAT = "Project name: %s%n\t" +
                                                "Project Description: %s%n\t" +
                                                "Project Start Date: %s%n\t" +
                                                "Project End Date: %s%n";

    public static void main(String[] args) {

        EntityManager em = Persistence.createEntityManagerFactory(DATABASE_NAME)
                .createEntityManager();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT);

        em.createQuery(GET_PROJECTS, Project.class)
                .setMaxResults(10)
                .getResultList()
                .stream()
                .sorted(Comparator.comparing(Project::getName))
                .forEach(project -> System.out.printf(PRINT_FORMAT,
                        project.getName(), project.getDescription(),
                        project.getStartDate().format(formatter),
                        project.getEndDate() == null ? "null" : project.getEndDate().format(formatter)
                ));

        em.close();
    }
}
