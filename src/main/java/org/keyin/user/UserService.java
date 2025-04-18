package org.keyin.user;

import java.sql.SQLException;

import org.keyin.user.childclasses.Admin;
import org.keyin.user.childclasses.Member;
import org.keyin.user.childclasses.Trainer;

public class UserService {
    private UserDao userDao;
    //private static final List<User> userList = new ArrayList<>();

    // Constructor
    public UserService() {
        this.userDao = new UserDao();
    }

    // Get user by username
    public User getUserByUsername(String username) {
        try {
            return userDao.getUserByUsername(username);
        } catch (SQLException e) {
            System.err.println("Error fetching user: " + e.getMessage());
            return null;
        }
    }

    // Get user by user role
    public User getUserByUserRole(String role) {
        try {
            User user = userDao.getUserByUserRole(role);

            if (user != null) {
                // Return subclass based on role
                switch (user.getRole().toLowerCase()) {
                    case "admin":
                        return new Admin(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(),
                                user.getPhone(), user.getAddress(), user.getRole());
                    case "trainer":
                        return new Trainer(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(),
                                user.getPhone(), user.getAddress(), user.getRole());
                    case "member":
                        return new Member(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(),
                                user.getPhone(), user.getAddress(), user.getRole());
                    default:
                        return user; // If unknown role, return base User
                }
            } else {
                return null; // No user found
            }

        } catch (SQLException e) {
            System.err.println("Error fetching user by role: " + e.getMessage());
            return null;
        }
    }

    // Basic login check (username + password)
    public boolean authenticateUser(String username, String password) {
        User user = getUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    // Register new user (simplified, would need validation)
    public boolean registerUser(String username, String password, String email, String phone, String address, String role) {
        User newUser = new User(0, username, password, email, phone, address, role); // ID will be set by DB
        try {
            return userDao.createUser(newUser);
        } catch (SQLException e) {
            System.err.println("Error registering user: " + e.getMessage());
            return false;
        }
    }

    public void printAllUsersInSystem() throws SQLException {
        userDao.getAllUsers();
    }

    public boolean deleteUser(int userId) {
        try {
            userDao.deleteUser(userId); // Call the delete method from UserDao
            System.out.println("User with ID " + userId + " has been deleted successfully.");
            return true;
        } catch (SQLException e) {
            System.err.println("Error deleting user with ID " + userId + ": " + e.getMessage());
            return false;
        }
    }


}
