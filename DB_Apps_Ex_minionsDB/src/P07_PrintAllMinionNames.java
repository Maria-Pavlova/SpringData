import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class P07_PrintAllMinionNames {
    private static final String GET_MINION_NAMES = "SELECT m.name FROM minions m";

    public static void main(String[] args) throws SQLException {

        Connection connection = Utils.getSQLConnection();

        PreparedStatement statement = connection.prepareStatement(GET_MINION_NAMES);

        ResultSet resultSet = statement.executeQuery();

        List<String> names = new ArrayList<>();

        while (resultSet.next()) {
            String name = resultSet.getString(Constants.COLUMN_NAME);
            names.add(name);
        }
        for (int f = 0, l = names.size() - 1; f <= l; f++, l--) {
            System.out.println(names.get(f));
            if (f != l) {
                System.out.println(names.get(l));
            }
        }
        connection.close();
    }
}
