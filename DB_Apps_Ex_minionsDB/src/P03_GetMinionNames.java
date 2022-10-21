import java.sql.*;
import java.util.Scanner;

public class P03_GetMinionNames {
    private static final String GET_VILLAIN_ID = "SELECT name FROM  villains WHERE id = ?";
    private static final String GET_MINIONS_NAMES_AND_AGE_BY_V_ID =
            "SELECT name, age " +
                    "FROM minions AS m " +
                    "JOIN minions_villains AS mv ON m.id = mv.minion_id " +
                    "WHERE mv.villain_id = ?";

    private static String NO_VILLAIN = "No villain with ID %d exists in the database.";
    private static final String VILLAIN_FORMAT = "Villain: %s%n";
    private static final String MINION_FORMAT = "%d. %s %d%n";

    public static void main(String[] args) throws SQLException {

        Connection connection = Utils.getSQLConnection();

        Scanner scanner = new Scanner(System.in);
        int villainId = Integer.parseInt(scanner.nextLine());

        PreparedStatement villainState = connection.prepareStatement(GET_VILLAIN_ID);
        villainState.setInt(1, villainId);

        ResultSet villainSet = villainState.executeQuery();

        if (!villainSet.next()) {
            System.out.printf(NO_VILLAIN, villainId);
            connection.close();
            return;
        }
        String vName = villainSet.getString(Constants.COLUMN_NAME);
        System.out.printf(VILLAIN_FORMAT, vName);


        PreparedStatement statementMinion = connection.prepareStatement(GET_MINIONS_NAMES_AND_AGE_BY_V_ID);
        statementMinion.setInt(1, villainId);

        ResultSet minionSet = statementMinion.executeQuery();

        for (int i = 1; minionSet.next(); i++) {
            String mName = minionSet.getString(Constants.COLUMN_NAME);
            int age = minionSet.getInt(Constants.COLUMN_AGE);
            System.out.printf(MINION_FORMAT, i, mName, age);
        }

        connection.close();
    }
}

