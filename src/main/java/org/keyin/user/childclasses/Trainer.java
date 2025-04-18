package org.keyin.user.childclasses;

import java.util.ArrayList;
import java.util.List;

import org.keyin.memberships.Membership;
import org.keyin.user.User;

public class Trainer extends User {
    private String specialty;
    private int yearsOfExperience;
    private List<Membership> assignedMemberships;

    // Constructor
    public Trainer(int userId, String username, String email, String password, String phone, String address, String role) {
        super(userId, username, email, password, phone, address, "trainer"); // Adding role "trainer"
        this.specialty = specialty;
        this.yearsOfExperience = yearsOfExperience;
        this.assignedMemberships = new ArrayList<>();
    }

    // Getters and Setters
    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public List<Membership> getAssignedMemberships() {
        return assignedMemberships;
    }

    public void assignMembership(Membership membership) {
        assignedMemberships.add(membership);
    }

    @Override
    public String toString() {
        return "Trainer{" +
                "userId=" + getId() +
                ", username='" + getUsername() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", phone='" + getPhone() + '\'' +
                ", address='" + getAddress() + '\'' +
                ", role='" + getRole() + '\'' +
                '}';
    }
}
