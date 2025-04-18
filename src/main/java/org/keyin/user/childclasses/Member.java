package org.keyin.user.childclasses;

import java.util.ArrayList;
import java.util.List;

import org.keyin.memberships.Membership;
import org.keyin.user.User;

public class Member extends User {

    private int membershipId;
    private String membershipLevel;
    private int creditsRemaining;
    private List<Membership> memberships;

    public Member(int userId, String username, String email, String password, String phone, String address, String role) {
        super(userId, username, email, password, phone, address, "member");
        this.membershipId = membershipId;
        this.membershipLevel = membershipLevel;
        this.creditsRemaining = creditsRemaining;
        this.memberships = new ArrayList<>();
    }

    // Getters and Setters
    public int getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(int membershipId) {
        this.membershipId = membershipId;
    }

    public String getMembershipLevel() {
        return membershipLevel;
    }

    public void setMembershipLevel(String membershipLevel) {
        this.membershipLevel = membershipLevel;
    }

    public int getCreditsRemaining() {
        return creditsRemaining;
    }

    public void setCreditsRemaining(int creditsRemaining) {
        this.creditsRemaining = creditsRemaining;
    }

    public List<Membership> getMemberships() {
        return memberships;
    }

    public void addMembership(Membership membership) {
        memberships.add(membership);
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
