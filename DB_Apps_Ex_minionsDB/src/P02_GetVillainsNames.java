import java.sql.*;


public class P02_GetVillainsNames {
    private static final String GET_VILLAINS_NAMES =
            "SELECT v.name, (COUNT(DISTINCT(mv.minion_id))) AS 'count' " +
                    "FROM villains AS v " +
                    "JOIN minions_villains AS mv ON v.id = mv.villain_id " +
                    "GROUP BY v.name " +
                    "HAVING `count` > ? " +
                    "ORDER BY `count` desc";

    private static final String COLUMN_COUNT = "count";

    public static void main(String[] args) throws SQLException {

        final Connection connection = Utils.getSQLConnection();

        PreparedStatement statement = connection.prepareStatement(GET_VILLAINS_NAMES);

        statement.setInt(1,15);

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()){
            String name = resultSet.getString(Constants.COLUMN_NAME);
            Integer count = resultSet.getInt(COLUMN_COUNT);
            System.out.printf("%s %d",name,count);
        }
        connection.close();
    }
}
