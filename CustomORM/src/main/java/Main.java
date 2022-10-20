import Entities.User;
import orm.EntityManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

import static orm.MyConnector.createConnection;
import static orm.MyConnector.getConnection;

public class Main {
    public static void main(String[] args) throws SQLException, IllegalAccessException {

       createConnection("root", "workbench", "custom-orm");
       Connection connection = getConnection();
        EntityManager<User> userEntityManager = new EntityManager<>(connection);
        User user = new User("Bob", 25, LocalDate.now());
        userEntityManager.persist(user);

    }
}
