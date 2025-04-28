package assignment9;
import java.sql.*;
import java.util.Scanner;

public class DeleteDemo {

    // Update these constants with your database credentials
    private static final String DB_URL = "jdbc:mysql://localhost:3306/DeleteDemo";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Delete User Record ===");

        System.out.print("Enter email of user to delete: ");
        String email = scanner.nextLine();

        deleteUserByEmail(email);

        scanner.close();
    }

    public static void deleteUserByEmail(String email) {
        String deleteQuery = "DELETE FROM register WHERE email = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(deleteQuery)) {

            stmt.setString(1, email);

            int rowsDeleted = stmt.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("User record deleted successfully.");
            } else {
                System.out.println("No user found with that email.");
            }

        } catch (SQLException e) {
            System.err.println("Error deleting user: " + e.getMessage());
        }
    }
}
