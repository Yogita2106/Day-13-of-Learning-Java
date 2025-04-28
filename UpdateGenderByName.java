package assignment9;

import java.sql.*;
import java.util.Scanner;

public class UpdateGenderByName {

    // Update these constants with your MySQL database info
    private static final String DB_URL = "jdbc:mysql://localhost:3306/UpdateGenderByName";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Update User Gender ===");

        System.out.print("Enter user name: ");
        String name = scanner.nextLine();

        System.out.print("Enter new gender (Male/Female/Other): ");
        String gender = scanner.nextLine();

        updateGender(name, gender);

        scanner.close();
    }

    public static void updateGender(String name, String gender) {
        String updateQuery = "UPDATE register SET gender = ? WHERE name = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(updateQuery)) {

            stmt.setString(1, gender);
            stmt.setString(2, name);

            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Gender updated successfully.");
            } else {
                System.out.println("No user found with that name.");
            }

        } catch (SQLException e) {
            System.err.println("Error updating gender: " + e.getMessage());
        }
    }
}

