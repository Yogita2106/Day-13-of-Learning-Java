package assignment9;
import java.sql.*;
import java.util.Scanner;
public class LoginDemo {
        private static final String DB_URL = "jdbc:mysql://localhost:3306/emp";
        private static final String DB_USER = "root";
        private static final String DB_PASSWORD = "root";

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("=== User Login ===");
            System.out.print("Enter email: ");
            String email = scanner.nextLine();

            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            if (authenticateUser(email, password)) {
                System.out.println("Login successful!");
            } else {
                System.out.println("Invalid email or password.");
            }

            scanner.close();
        }

        public static boolean authenticateUser(String email, String password) {
            boolean isAuthenticated = false;
            String query = "SELECT * FROM register WHERE email = ? AND password = ?";

            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 PreparedStatement stmt = conn.prepareStatement(query)) {

                stmt.setString(1, email);
                stmt.setString(2, password);

                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    isAuthenticated = true;
                }

            } catch (SQLException e) {
                System.err.println("Database error: " + e.getMessage());
            }
            return isAuthenticated;
        }
    }


