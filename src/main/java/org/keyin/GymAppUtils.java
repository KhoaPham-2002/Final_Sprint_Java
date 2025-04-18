package org.keyin;

import java.sql.SQLException;

import org.keyin.memberships.Membership;
import org.keyin.memberships.MembershipDAO;
import org.keyin.memberships.MembershipService;
import org.keyin.user.User;
import org.keyin.user.UserService;
import org.keyin.workoutclasses.WorkoutClass;
import org.keyin.workoutclasses.WorkoutClassService;

public class GymAppUtils {
    private static final UserService userService = new UserService();
    private static final WorkoutClassService workoutService = new WorkoutClassService();
    private static final MembershipService membershipService = new MembershipService(new MembershipDAO());
    private static User loggedInUser = null;

    // Getter method to access the userService
    public static UserService getUserService() {
        return userService;
    }

    // Getter method to access the logged-in user
    public static User getLoggedInUser() {
        return loggedInUser;  
    }

    // Register a new user and print result with error details if registration fails
    public static void registerTestUser(String username, String email, String password, String phoneNumber, String address, String role) {
        if (userService.getUserByUsername(username) == null) {
            boolean registrationSuccess = userService.registerUser(username, email, password, phoneNumber, address, role);
            if (registrationSuccess) {
                System.out.println("Registration successful for user: " + username + " with role: " + role);
            } else {
                System.out.println("Registration failed for user: " + username);
            }
        } else {
            System.out.println("Duplicate username detected: " + username);
            System.out.println("Registration failed for user: " + username);
        }
    }
    

    // Login method now returns nothing but prints success/failure.
    public static void loginAndPrintUser(String username, String password) {
        if (userService.authenticateUser(username, password)) {
            User basicUser = userService.getUserByUsername(username);
            if (basicUser != null) {
                loggedInUser = userService.getUserByUserRole(basicUser.getRole());
                System.out.println("Login successful for user: " + username);
                System.out.println("User role: " + loggedInUser.getRole());
            } else {
                System.out.println("Login failed: user not found.");
            }
        } else {
            System.out.println("Login failed for user: " + username);
        }
    }
    
    public static void displayAllUsers() {
        try {
            userService.printAllUsersInSystem();
        } catch (SQLException e) {
            System.err.println("Error fetching users: " + e.getMessage());
        }
    }

    public static void deleteUserFromSystem(int userId) {
        boolean isDeleted = userService.deleteUser(userId);
        if (isDeleted) {
            System.out.println("User with ID " + userId + " has been deleted from the system.");
        } else {
            System.out.println("Failed to delete user with ID " + userId + ".");
        }
    }

    // Print all memberships in the system
    public static void printAllMemberships() {
        try {
            membershipService.printAllMembershipsInSystem();
        } catch (SQLException e) {
            System.err.println("Error fetching memberships: " + e.getMessage());
        }
    }

    // Add a new membership
    public static void addMembership(Membership membership) {
        try {
            membershipService.addMembership(membership);
            System.out.println("Membership added successfully!");
        } catch (SQLException | IllegalArgumentException e) {
            System.err.println("Error adding membership: " + e.getMessage());
        }
    }

    // Update a membership
    public static void updateMembership(Membership membership) {
        try {
            membershipService.updateMembership(membership);
            System.out.println("Membership updated successfully!");
        } catch (SQLException | IllegalArgumentException e) {
            System.err.println("Error updating membership: " + e.getMessage());
        }
    }

    // Delete a membership by ID
    public static void deleteMembership(int id) {
        try {
            membershipService.deleteMembership(id);
            System.out.println("Membership with ID " + id + " deleted successfully!");
        } catch (SQLException e) {
            System.err.println("Error deleting membership: " + e.getMessage());
        }
    }

    public static void printAllMembershipsByCustomerId(int id) {
        try {
            System.out.println("Membership details");
            membershipService.printAllMembershipsByCustomerId(id);
        } catch (SQLException e) {
            System.err.println("Error retrieving memberships for customer ID " + id + ": " + e.getMessage());
        }
    }

    public static void printTotalRevenue() {
        try {
            System.out.println("Calculating total membership revenue...");
            membershipService.printTotalRevenue();
        } catch (SQLException e) {
            System.err.println("Error calculating total revenue: " + e.getMessage());
        }
    }

    // Print all workout classes in the system
    public static void printAllWorkoutClasses() {
        try {
            System.out.println("Workout classes in the system:");
            workoutService.printAllWorkoutClassesInSystem();
        } catch (SQLException e) {
            System.err.println("Error retrieving workout classes: " + e.getMessage());
        }
    }

    // Create a new workout class
    public static void createWorkoutClass(WorkoutClass workoutClass) {
        try {
            workoutService.createWorkoutClass(workoutClass);
            System.out.println("Workout class created successfully.");
        } catch (SQLException e) {
            System.err.println("Error creating workout class: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    // Update an existing workout class
    public static void updateWorkoutClass(WorkoutClass workoutClass) {
        try {
            workoutService.updateWorkoutClass(workoutClass);
            System.out.println("Workout class updated successfully.");
        } catch (SQLException e) {
            System.err.println("Error updating workout class: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    // Delete a workout class by ID
    public static void deleteWorkoutClass(int id) {
        try {
            workoutService.deleteWorkoutClass(id);
            System.out.println("Workout class deleted successfully.");
        } catch (SQLException e) {
            System.err.println("Error deleting workout class: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    
    

}
