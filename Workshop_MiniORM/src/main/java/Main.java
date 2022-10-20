import Entities.Address;
import Entities.User;
import orm.EntityManager;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

import static orm.MyConnector.createConnection;
import static orm.MyConnector.getConnection;

public class Main {
    public static void main(String[] args) throws SQLException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {

       createConnection("root", "workbench", "custom-orm");
       Connection connection = getConnection();
        EntityManager<User> userEntityManager = new EntityManager<>(connection);
        EntityManager<Address> addressEntityManager = new EntityManager<>(connection);

        addressEntityManager.doCreate(Address.class);

        User user = new User("Bob", 25, LocalDate.now());
//
//        user.setId(1);
        user.setUsername("Bob_new4");
        userEntityManager.persist(user);

//        //userEntityManager.doCreate(User.class);
//        //userEntityManager.doAlter(User.class);


        Iterable<User> first = userEntityManager.find(User.class);
        System.out.println(first.toString());

        User toDelete = userEntityManager.findFirst(User.class, "id > 4");
        System.out.println(toDelete.toString());

        userEntityManager.delete(toDelete);

        Iterable<User> second = userEntityManager.find(User.class);
        System.out.println(second.toString());

        connection.close();
    }
}
