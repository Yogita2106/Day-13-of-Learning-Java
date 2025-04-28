package assignment9;

import java.sql.*;
import java.util.Scanner;

public class UpdateDemo {

    // Update with your database connection info
    private static final String DB_URL = "jdbc:mysql://localhost:3306/UpdateDemo";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Update User City ===");

        System.out.print("Enter user email: ");
        String email = scanner.nextLine();

        System.out.print("Enter new city: ");
        String newCity = scanner.nextLine();

        updateCity(email, newCity);

        scanner.close();
    }

    public static void updateCity(String email, String newCity) {
        String updateQuery = "UPDATE register SET city = ? WHERE email = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(updateQuery)) {

            stmt.setString(1, newCity);
            stmt.setString(2, email);

            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("City updated successfully.");
            } else {
                System.out.println("No user found with that email.");
            }

        } catch (SQLException e) {
            System.err.println("Error updating city: " + e.getMessage());
        }
    }
}
