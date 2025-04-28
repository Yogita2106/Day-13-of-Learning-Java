package assignment9;

import java.sql.*;
import java.util.Scanner;

public class FetchByGender {

    // Update with your MySQL DB credentials
    private static final String DB_URL = "jdbc:mysql://localhost:3306/FetchByGender";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Fetch Users by Gender ===");

        System.out.print("Enter gender to filter by (Male/Female/Other): ");
        String gender = scanner.nextLine();

        fetchUsersByGender(gender);

        scanner.close();
    }

    public static void fetchUsersByGender(String gender) {
        String query = "SELECT * FROM register WHERE gender = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, gender);

            ResultSet rs = stmt.executeQuery();

            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("Gender: " + rs.getString("gender"));
                System.out.println("City: " + rs.getString("city"));
                System.out.println("--------------------------");
            }

            if (!found) {
                System.out.println("No users found with gender: " + gender);
            }

        } catch (SQLException e) {
            System.err.println("Error fetching users: " + e.getMessage());
        }
    }
}
