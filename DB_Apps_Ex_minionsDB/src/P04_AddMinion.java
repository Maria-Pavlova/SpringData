import java.sql.*;
import java.util.Scanner;

public class P04_AddMinion {
    private static final String GET_TOWN_BY_NAME = "SELECT id FROM towns WHERE name = ?";
    private static final String INSERT_TOWN = "INSERT INTO towns(name) VALUES (?)";
    private static final String INSERT_MINION = "INSERT INTO minions(name, age, town_id) VALUES (?, ?, ?)";
    private static final String GET_LAST_MINION = "SELECT id FROM minions ORDER BY id DESC LIMIT 1";
    private static final String INSERT_MINION_VILLAIN = "INSERT INTO minions_villains VALUES (?, ?)";
    private static final String GET_VILLAIN = "SELECT id FROM villains WHERE name = ?";
    private static final String INSERT_VILLAIN = "INSERT INTO villains(name ,evilness_factor) VALUES (?, ?)";


    public static void main(String[] args) throws SQLException {

        final Connection connection = Utils.getSQLConnection();

        Scanner scanner = new Scanner(System.in);
        String[] minionData = scanner.nextLine().split(" ");
        String minionName = minionData[1];
        int minionAge = Integer.parseInt(minionData[2]);
        String minionTown = minionData[3];
        String villainName = scanner.nextLine().split(" ")[1];

        int townId = getOrInsertTown(connection, minionTown);
        int villainId = getOrInsertVillain(connection, villainName);

        insertMinion(connection, minionName, minionAge, townId);

        int lastMinionId = getLastMinionId(connection);

        insertIntoMinionsVillains(connection, villainId, lastMinionId);

        System.out.printf("Successfully added %s to be minion of %s.",minionName, villainName);

        connection.close();
    }

    private static void insertIntoMinionsVillains(Connection connection, int villainId, int lastMinionId) throws SQLException {
        PreparedStatement insertMinionVillain = connection.prepareStatement(INSERT_MINION_VILLAIN);
        insertMinionVillain.setInt(1, lastMinionId);
        insertMinionVillain.setInt(2, villainId);
        insertMinionVillain.executeUpdate();
    }

    private static int getLastMinionId(Connection connection) throws SQLException {
        PreparedStatement getLastMinion = connection.prepareStatement(GET_LAST_MINION);
        ResultSet lastMinionSet = getLastMinion.executeQuery();
        lastMinionSet.next();
        int lastMinionId = lastMinionSet.getInt(Constants.COLUMN_ID);
        return lastMinionId;
    }

    private static void insertMinion(Connection connection, String minionName, int minionAge, int townId) throws SQLException {
        PreparedStatement insertMinion = connection.prepareStatement(INSERT_MINION);
        insertMinion.setString(1, minionName);
        insertMinion.setInt(2, minionAge);
        insertMinion.setInt(3, townId);
        insertMinion.executeUpdate();
    }

    private static int getOrInsertVillain(Connection connection, String villainName) throws SQLException {
        PreparedStatement selectVillain = connection.prepareStatement(GET_VILLAIN);
        selectVillain.setString(1, villainName);
        ResultSet villainSet = selectVillain.executeQuery();

        int villainId = 0;
        if (!villainSet.next()){
            PreparedStatement insertVillain = connection.prepareStatement(INSERT_VILLAIN);
            insertVillain.setString(1, villainName);
            insertVillain.setString(2, "evil");

            insertVillain.executeUpdate();
            ResultSet newVillainSet = insertVillain.executeQuery();
            newVillainSet.next();
            villainId = newVillainSet.getInt(Constants.COLUMN_ID);
            System.out.printf("Villain %s was added to the database.%n",villainName);
        }else {
           villainId = villainSet.getInt(Constants.COLUMN_ID);
        }
        return villainId;
    }

    private static int getOrInsertTown(Connection connection, String minionTown) throws SQLException {
        PreparedStatement townState = connection.prepareStatement(GET_TOWN_BY_NAME);
        townState.setString(1, minionTown);

        ResultSet townSet = townState.executeQuery();
        int townId = 0;
        if (!townSet.next()){
            PreparedStatement insertTown = connection.prepareStatement(INSERT_TOWN);
            insertTown.setString(1, minionTown);
            insertTown.executeUpdate();
            
            ResultSet newTownSet = townState.executeQuery();
            newTownSet.next();
            townId = newTownSet.getInt(Constants.COLUMN_ID);
            System.out.printf("Town %s was added to the database.%n",minionTown);
        }else {
            townId = townSet.getInt(Constants.COLUMN_ID);
        }
        return townId;
    }

}
