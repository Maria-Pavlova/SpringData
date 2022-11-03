package p06_FootballBettingDatabase;

import p06_FootballBettingDatabase.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class Main06 {
    public static void main(String[] args) {

        // P01 Gringotts Database is in this project too

        EntityManager entityManager = Persistence.createEntityManagerFactory("football_betting")
                .createEntityManager();
        entityManager.getTransaction().begin();

        User user = new User("user1", "pass1", "user@mail.bg", "Ivan Ivanov", BigDecimal.valueOf(1500));
        entityManager.persist(user);

        entityManager.getTransaction().commit();


        entityManager.close();
    }
}
