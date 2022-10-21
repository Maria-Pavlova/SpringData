import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class P05_ChangeTownNamesCasing {
    private static final String GET_TOWNS = "SELECT name FROM towns WHERE country = ?";
    private static final String UPDATE_TOWNS = "UPDATE towns SET name = UPPER(name) WHERE country = ?";

    public static void main(String[] args) throws SQLException {

        final Connection connection = Utils.getSQLConnection();

        Scanner scanner = new Scanner(System.in);
        String country = scanner.nextLine();

        PreparedStatement getTowns = connection.prepareStatement(GET_TOWNS);
        getTowns.setString(1, country);
        ResultSet townsSet = getTowns.executeQuery();

        if (!townsSet.next()) {
            System.out.println("No town names were affected.");
            return;
        } else {
            PreparedStatement updateTowns = connection.prepareStatement(UPDATE_TOWNS);
            updateTowns.setString(1, country);
            int updatedCount = updateTowns.executeUpdate();
            System.out.println(updatedCount + " town names were affected.");

            ResultSet newTownsSet = getTowns.executeQuery();

            List<String> towns = new ArrayList<>();

            while (newTownsSet.next()) {
                String townName = newTownsSet.getString(Constants.COLUMN_NAME);
                towns.add(townName);
            }
            System.out.println(towns);

        }
        connection.close();

    }
}
