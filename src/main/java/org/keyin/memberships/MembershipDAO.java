package org.keyin.memberships;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.keyin.database.DatabaseConnection;

/**
 * DAO class for handling CRUD operations related to Memberships.
 */
public class MembershipDAO {

    // Column name constants for reuse
    private static final String COL_ID = "id";
    private static final String COL_TYPE = "membership_type";
    private static final String COL_DESCRIPTION = "membership_description";
    private static final String COL_COST = "membership_cost";
    private static final String COL_CUSTOMER_ID = "customer_id";
    private static final String COL_VALID_FROM = "valid_from";
    private static final String COL_VALID_UNTIL = "valid_until";
    private static final String COL_CREDITS = "credits_remaining";

    /**
     * Inserts a new membership record into the database.
     */
    public void addMembership(Membership membership) throws SQLException {
        String sql = "INSERT INTO memberships (" +
                     COL_TYPE + ", " + COL_DESCRIPTION + ", " + COL_COST + ", " +
                     COL_CUSTOMER_ID + ", " + COL_VALID_FROM + ", " + COL_VALID_UNTIL + ", " + COL_CREDITS + ") " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, membership.getType());
            pstmt.setString(2, membership.getDetails());
            pstmt.setDouble(3, membership.getCost());
            pstmt.setInt(4, membership.getCustomerId());
            pstmt.setDate(5, Date.valueOf(membership.getValidFrom()));
            pstmt.setDate(6, Date.valueOf(membership.getValidUntil()));
            pstmt.setInt(7, membership.getCreditsRemaining());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error adding membership: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Retrieves a membership by its ID.
     */
    public Membership getMembershipById(int id) throws SQLException {
        String sql = "SELECT * FROM memberships WHERE " + COL_ID + " = ?";
        Membership membership = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                membership = mapResultSetToMembership(rs);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving membership: " + e.getMessage());
            throw e;
        }

        return membership;
    }

    public void getMembershipByCustomerId(int customerId) throws SQLException {
        ResultSet rs = null;
        String sql = "SELECT * FROM memberships WHERE " + COL_CUSTOMER_ID + " = ?";
    
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
    
            pstmt.setInt(1, customerId);
            rs = pstmt.executeQuery();
    
            if (rs.next()) {
                int id = rs.getInt("id");
                String membershipType = rs.getString("membership_type");
                String membershipDescription = rs.getString("membership_description");
                double membershipCost = rs.getDouble("membership_cost");
                Date validFrom = rs.getDate("valid_from");
                Date validUntil = rs.getDate("valid_until");
                int creditsRemaining = rs.getInt("credits_remaining");
    
                System.out.println("-------------------------------");
                System.out.println("Membership ID: " + id);
                System.out.println("Membership Type: " + membershipType);
                System.out.println("Description: " + membershipDescription);
                System.out.println("Cost: " + membershipCost);
                System.out.println("Customer ID: " + customerId);
                System.out.println("Valid From: " + validFrom);
                System.out.println("Valid Until: " + validUntil);
                System.out.println("Credits Remaining: " + creditsRemaining);
                System.out.println("-------------------------------");
            } else {
                System.out.println("No membership found for customer ID: " + customerId);
            }
    
        } catch (SQLException e) {
            System.out.println("Error retrieving membership by customer ID: " + e.getMessage());
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
    }
    
    

    /**
     * Retrieves all membership records from the database.
     */
    public void getAllMemberships() throws SQLException {
        ResultSet rs = null;
        String sql = "SELECT * FROM memberships";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            rs = pstmt.executeQuery();
            System.out.println("Memberships in the database:");
            while (rs.next()) {
                int id = rs.getInt("id");
                String membershipType = rs.getString("membership_type");
                String membershipDescription = rs.getString("membership_description");
                double membershipCost = rs.getDouble("membership_cost");
                int customerId = rs.getInt("customer_id");
                Date validFrom = rs.getDate("valid_from");
                Date validUntil = rs.getDate("valid_until");
                int creditsRemaining = rs.getInt("credits_remaining");
    
                System.out.println("-------------------------------");
                System.out.println("Membership ID: " + id);
                System.out.println("Membership Type: " + membershipType);
                System.out.println("Description: " + membershipDescription);
                System.out.println("Cost: " + membershipCost);
                System.out.println("Customer ID: " + customerId);
                System.out.println("Valid From: " + validFrom);
                System.out.println("Valid Until: " + validUntil);
                System.out.println("Credits Remaining: " + creditsRemaining);
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

    /**
     * Updates an existing membership record.
     */
    public void updateMembership(Membership membership) throws SQLException {
        String sql = "UPDATE memberships SET " +
                     COL_TYPE + " = ?, " +
                     COL_DESCRIPTION + " = ?, " +
                     COL_COST + " = ?, " +
                     COL_CUSTOMER_ID + " = ?, " +
                     COL_VALID_FROM + " = ?, " +
                     COL_VALID_UNTIL + " = ?, " +
                     COL_CREDITS + " = ? " +
                     "WHERE " + COL_ID + " = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, membership.getType());
            pstmt.setString(2, membership.getDetails());
            pstmt.setDouble(3, membership.getCost());
            pstmt.setInt(4, membership.getCustomerId());
            pstmt.setDate(5, Date.valueOf(membership.getValidFrom()));
            pstmt.setDate(6, Date.valueOf(membership.getValidUntil()));
            pstmt.setInt(7, membership.getCreditsRemaining());
            pstmt.setInt(8, membership.getId());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating membership: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Deletes a membership record from the database by ID.
     */
    public void deleteMembership(int id) throws SQLException {
        String sql = "DELETE FROM memberships WHERE " + COL_ID + " = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error deleting membership: " + e.getMessage());
            throw e;
        }
    }

    public double getTotalRevenue() throws SQLException {
        String sql = "SELECT SUM(" + COL_COST + ") AS total_revenue FROM memberships";
        double totalRevenue = 0.0;
    
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
    
            if (rs.next()) {
                totalRevenue = rs.getDouble("total_revenue");
            }
    
        } catch (SQLException e) {
            System.out.println("Error calculating total revenue: " + e.getMessage());
            throw e;
        }
    
        return totalRevenue;
    }

    /**
     * Maps a ResultSet row to a Membership object.
     */
    private Membership mapResultSetToMembership(ResultSet rs) throws SQLException {
        return new Membership(
                rs.getInt(COL_ID),
                rs.getString(COL_TYPE),
                rs.getString(COL_DESCRIPTION),
                rs.getDouble(COL_COST),
                rs.getInt(COL_CUSTOMER_ID),
                rs.getDate(COL_VALID_FROM).toLocalDate(),
                rs.getDate(COL_VALID_UNTIL).toLocalDate(),
                rs.getInt(COL_CREDITS)
        );
    }
}
