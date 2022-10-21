import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class P08_IncreaseMinionsAge {
    private static final String UPDATE_MINIONS_BY_ID = "UPDATE minions SET age = age + 1, " +
            "name = lower(name) WHERE id IN (%s)";
    private static final String GET_MINIONS = "SELECT name, age FROM minions";

    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter ids");

        List<String> ids = Arrays.stream(scanner.nextLine().split("\\s+"))
                .collect(Collectors.toList());

        Connection connection = Utils.getSQLConnection();

        String query = String.format(UPDATE_MINIONS_BY_ID,
                ids
                        .stream()
                        .map(id -> "?")
                        .collect(Collectors.joining(", ")));

        PreparedStatement updateMinions = connection.prepareStatement(query);
        for (int i = 1; i <= ids.size(); i++) {
            updateMinions.setInt(i, Integer.parseInt(ids.get(i - 1)));
        }

        updateMinions.executeUpdate();

        P09_IncreaseAgeStoredProcedure.printMinionsNameAndAge(connection, GET_MINIONS);
    }
}
