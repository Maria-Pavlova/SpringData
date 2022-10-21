import java.sql.*;
import java.util.Scanner;

public class P06_RemoveVillain {
    private static final String GET_VILLAIN_NAME_BY_ID = "SELECT name FROM villains WHERE id = ?";
    private static final String GET_MINIONS_COUNT = "SELECT COUNT(DISTINCT minion_id) AS m_count " +
                                                     "FROM minions_villains WHERE villain_id = ?";
    private static final String DELETE_MINIONS_VILLAINS = "DELETE FROM minions_villains WHERE villain_id = ?";
    private static final String DELETE_VILLAIN = "DELETE FROM villains WHERE id = ?";

    public static void main(String[] args) throws SQLException {

        final Connection connection = Utils.getSQLConnection();

        Scanner scanner = new Scanner(System.in);
        int villainId = Integer.parseInt(scanner.nextLine());

        PreparedStatement selectVillain = connection.prepareStatement(GET_VILLAIN_NAME_BY_ID);
        selectVillain.setInt(1, villainId);

        ResultSet villainSet = selectVillain.executeQuery();

        if (!villainSet.next()){
            System.out.println("No such villain was found");
            connection.close();
            return;
        }

        String villainName = villainSet.getString(Constants.COLUMN_NAME);

        PreparedStatement minionsCount = connection.prepareStatement(GET_MINIONS_COUNT);
        minionsCount.setInt(1, villainId);

        ResultSet minionSet = minionsCount.executeQuery();
        minionSet.next();
        int mCount = minionSet.getInt("m_count");

        connection.setAutoCommit(false);
        try {
            PreparedStatement deleteMinionsVillains = connection.prepareStatement(DELETE_MINIONS_VILLAINS);
            deleteMinionsVillains.setInt(1, villainId);
            deleteMinionsVillains.executeUpdate();

            PreparedStatement deleteVillain = connection.prepareStatement(DELETE_VILLAIN);
            deleteVillain.setInt(1,villainId);
            deleteVillain.executeUpdate();

            connection.commit();
            System.out.println(villainName + " was deleted");
            System.out.println(mCount + " minions released");
        }catch (SQLException e){
            e.printStackTrace();
            connection.rollback();
        }

        connection.close();
    }
}
