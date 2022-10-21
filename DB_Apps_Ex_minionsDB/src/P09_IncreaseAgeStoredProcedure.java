import java.sql.*;
import java.util.Scanner;

public class P09_IncreaseAgeStoredProcedure {
    private static final String GET_MINIONS = "SELECT name, age FROM minions";
    private static final String CALL_PROCEDURE = "CALL usp_get_older(?)";

    public static void main(String[] args) throws SQLException {
        Connection connection = Utils.getSQLConnection();

        Scanner scanner = new Scanner(System.in);

        System.out.println("ENTER minion id:");
        int id = Integer.parseInt(scanner.nextLine());

        CallableStatement statement = connection.prepareCall(CALL_PROCEDURE);
        statement.setInt(1, id);
        statement.execute();

        printMinionsNameAndAge(connection, GET_MINIONS);
    }


    static void printMinionsNameAndAge(Connection connection, String getMinions2) throws SQLException {
        PreparedStatement getMinions = connection.prepareStatement(getMinions2);
        ResultSet resultSet = getMinions.executeQuery();

        while (resultSet.next()) {
            String name = resultSet.getString(Constants.COLUMN_NAME);
            int age = resultSet.getInt(Constants.COLUMN_AGE);
            System.out.println(name + " " + age);
        }
        connection.close();
    }
}
