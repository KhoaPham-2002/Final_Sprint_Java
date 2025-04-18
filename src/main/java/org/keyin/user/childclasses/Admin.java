package org.keyin.user.childclasses;

import java.util.ArrayList;
import java.util.List;

import org.keyin.memberships.Membership;
import org.keyin.user.User;

public class Admin extends User {

    private List<User> userList = new ArrayList<>();
    private List<Membership> membershipList = new ArrayList<>();

    
    // Constructor for Admin
    public Admin(int userId, String username, String email, String password, String phone, String address, String role) {
        super(userId, username, email, password, phone, address, "admin");
    }

    // Add a user to the user list
    public void addUser(User user) {
        userList.add(user);
    }

    // Delete a user by ID
    public void deleteUser(int userId) {
        boolean removed = userList.removeIf(user -> user.getId() == userId);
        if (removed) {
            System.out.println("User with ID " + userId + " deleted.");
        } else {
            System.out.println("No user found with ID " + userId);
        }
    }

    // View all users
    public void viewAllUsers() {
        System.out.println("--- All Users ---");
        if (userList.isEmpty()) {
            System.out.println("No users to display.");
        } else {
            for (User user : userList) {
                System.out.println(user);
            }
        }
    }

    // Add a membership to the membership list
    public void addMembership(Membership membership) {
        membershipList.add(membership);
    }

    
    // View all memberships and the total revenue
    public void viewMembershipsAndRevenue() {
        System.out.println("--- Gym Memberships ---");
        double totalRevenue = 0;
        for (Membership membership : membershipList) {
            System.out.println(membership);
            totalRevenue += membership.getCost();
        }
        System.out.println("Total Revenue: $%.2f%n" + totalRevenue);
    }

    @Override
    public String toString() {
        return "Admin{" +
                "userId=" + getId() +
                ", username='" + getUsername() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", phone='" + getPhone() + '\'' +
                ", address='" + getAddress() + '\'' +
                ", role='" + getRole() + '\'' +
                '}';
    }
}

