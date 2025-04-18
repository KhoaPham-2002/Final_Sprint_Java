package org.keyin.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.keyin.database.DatabaseConnection;

/**
 * Data Access Object for interacting with User data in the database.
 */
public class UserDao {

    /**
     * Retrieves a user from the database by username.
     * @param username The username of the user to retrieve.
     * @return The User object if found, otherwise null.
     * @throws SQLException If a database access error occurs.
     */
    public User getUserByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM app_user WHERE user_name = ?";

        try (Connection conn = DatabaseConnection.getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username); // Set the username parameter for the query
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // Create and return a User object with data from the result set
                    return new User(
                        rs.getInt("id"),
                        rs.getString("user_name"),
                        rs.getString("user_email"),
                        rs.getString("user_password"),
                        rs.getString("user_phone"),
                        rs.getString("user_address"),
                        rs.getString("user_role")
                    );
                }
            }
        }
        // Return null if no user is found
        return null;
    }

    public User getUserByUserRole(String role) throws SQLException {
        String sql = "SELECT * FROM app_user WHERE user_role = ?";
    
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
    
            pstmt.setString(1, role); // Set the role parameter for the query
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // Return a User object populated from the result set
                    return new User(
                        rs.getInt("id"),
                        rs.getString("user_name"),
                        rs.getString("user_email"),
                        rs.getString("user_password"),
                        rs.getString("user_phone"),
                        rs.getString("user_address"),
                        rs.getString("user_role")
                    );
                }
            }
        }
        // Return null if no user with the specified role is found
        return null;
    }    

    /**
     * Creates a new user in the database.
     * @param user The user to add to the database.
     * @throws SQLException If a database access error occurs.
     */
    public boolean createUser(User user) throws SQLException {
        String sql = "INSERT INTO app_user (user_name, user_email, user_password, user_phone, user_address, user_role) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getPhone());
            pstmt.setString(5, user.getAddress());
            pstmt.setString(6, user.getRole());
    
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            // You can log the error or handle it specifically
            if (e.getMessage().contains("duplicate key value")) {
                System.out.println("Duplicate email detected: " + user.getEmail());
                return false;
            }
            throw e;
        }
    }
    

    /**
     * Updates an existing user's password in the database.
     * @param user The user whose password needs to be updated.
     * @throws SQLException If a database access error occurs.
     */
    public void updateUserPassword(User user) throws SQLException {
        String sql = "UPDATE app_user SET user_password = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getPassword());
            pstmt.setInt(2, user.getId());
            pstmt.executeUpdate(); // Execute the update query
        }
    }

    /**
     * Deletes a user from the database by their ID.
     * @param userId The ID of the user to delete.
     * @throws SQLException If a database access error occurs.
     */
    public void deleteUser(int userId) throws SQLException {
        String sql = "DELETE FROM app_user WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            pstmt.executeUpdate(); // Execute the delete query
        }
    }

    public void getAllUsers() throws SQLException {
        ResultSet rs = null;
        String sql = "SELECT * FROM app_user";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            rs = pstmt.executeQuery();
            System.out.println("Users in the database:");
            while (rs.next()) {
                int userId = rs.getInt("id");
                String username = rs.getString("user_name");
                String email = rs.getString("user_email");
                String password = rs.getString("user_password");
                String phone = rs.getString("user_phone");
                String address = rs.getString("user_address");
                String role = rs.getString("user_role");


                System.out.println("-------------------------------");
                System.out.println("User ID: " + userId);
                System.out.println("Username: " + username);
                System.out.println("Email: " + email);
                System.out.println("Password: " + password);
                System.out.println("Phone: " + phone);
                System.out.println("Address: " + address);
                System.out.println("Role: " + role);
                System.out.println("-------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;  // Propagate the exception after printing stack trace
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
    }
}


