package org.keyin.memberships;

import java.sql.SQLException;

/**
 * Service class for handling business logic related to Memberships.
 * This class uses the MembershipDAO to interact with the database.
 */
public class MembershipService {

    // Dependency Injection: Injecting the MembershipDAO instance
    private final MembershipDAO dao;

    // Constructor to inject the MembershipDAO
    public MembershipService(MembershipDAO dao) {
        this.dao = dao;
    }

    /**
     * Adds a new membership.
     * @param membership the membership object to be added.
     * @throws SQLException if there is an error during the insertion.
     */
    public void addMembership(Membership membership) throws SQLException {
        // Business logic can be added here before calling DAO method, such as validation
        validateMembership(membership);
        dao.addMembership(membership);
    }

    /**
     * Retrieves a membership by its ID.
     * @param id the ID of the membership to retrieve.
     * @return the Membership object if found, otherwise null.
     * @throws SQLException if there is an error during retrieval.
     */
    public Membership getMembershipById(int id) throws SQLException {
        return dao.getMembershipById(id);
    }

    /**
     * Retrieves all memberships.
     * @return a list of all memberships.
     * @throws SQLException if there is an error during retrieval.
     */
    public void printAllMembershipsInSystem() throws SQLException {
        dao.getAllMemberships();
    }

    public void printAllMembershipsByCustomerId(int id) throws SQLException {
        dao.getMembershipByCustomerId(id);
    }

    /**
     * Updates an existing membership.
     * @param membership the membership object to be updated.
     * @throws SQLException if there is an error during the update.
     */
    public void updateMembership(Membership membership) throws SQLException {
        // You can add any business logic before updating, like checking if it's expired, etc.
        validateMembership(membership);
        dao.updateMembership(membership);
    }

    /**
     * Deletes a membership by its ID.
     * @param id the ID of the membership to delete.
     * @throws SQLException if there is an error during deletion.
     */
    public void deleteMembership(int id) throws SQLException {
        dao.deleteMembership(id);
    }

    /**
     * Validates the membership details before performing any database operations.
     * For example, checking that membership details are not empty or invalid.
     * @param membership the membership object to validate.
     * @throws IllegalArgumentException if validation fails.
     */
    private void validateMembership(Membership membership) {
        // Example: Check that cost is positive
        if (membership.getCost() <= 0) {
            throw new IllegalArgumentException("Membership cost must be positive.");
        }
        // Add any other validation as necessary
        if (membership.getValidFrom().isAfter(membership.getValidUntil())) {
            throw new IllegalArgumentException("The valid from date cannot be after the valid until date.");
        }
    }

    public void printTotalRevenue() throws SQLException {
        double revenue = dao.getTotalRevenue();
        System.out.println("Total Revenue from Memberships: $" + revenue);
    }
    
}

