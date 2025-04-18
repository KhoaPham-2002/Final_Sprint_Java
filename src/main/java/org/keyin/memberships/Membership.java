package org.keyin.memberships;

import java.time.LocalDate;

/**
 * Represents a gym membership plan with details like cost, validity, and features.
 */
public class Membership {

    private int id;
    private String type;
    private String details;
    private double cost;
    private int customerId;
    private LocalDate validFrom;
    private LocalDate validUntil;
    private int creditsRemaining;

    public Membership() {
    }

    public Membership(int id, String type, String details, double cost,
                      int customerId, LocalDate validFrom,
                      LocalDate validUntil, int creditsRemaining) {
        this.id = id;
        this.type = type;
        this.details = details;
        this.cost = cost;
        this.customerId = customerId;
        this.validFrom = validFrom;
        this.validUntil = validUntil;
        this.creditsRemaining = creditsRemaining;
    }

    public Membership(int id, String type, String details, double cost, int customerId) {
        this.id = id;
        this.type = type;
        this.details = details;
        this.cost = cost;
        this.customerId = customerId;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getDetails() {
        return details;
    }

    public double getCost() {
        return cost;
    }

    public int getCustomerId() {
        return customerId;
    }

    public LocalDate getValidFrom() {
        return validFrom;
    }

    public LocalDate getValidUntil() {
        return validUntil;
    }

    public int getCreditsRemaining() {
        return creditsRemaining;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setCost(double cost) {
        if (cost < 0) throw new IllegalArgumentException("Cost cannot be negative");
        this.cost = cost;
    }    

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setValidFrom(LocalDate validFrom) {
        this.validFrom = validFrom;
    }

    public void setValidUntil(LocalDate validUntil) {
        this.validUntil = validUntil;
    }

    public void setCreditsRemaining(int creditsRemaining) {
        this.creditsRemaining = creditsRemaining;
    }

    public boolean isPremiumTier() {
        return type != null && (
                type.equalsIgnoreCase("Elite") ||
                type.equalsIgnoreCase("Pro") ||
                type.equalsIgnoreCase("VIP"));
    }

    public boolean deductCredit() {
        if (creditsRemaining > 0) {
            creditsRemaining--;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Membership{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", details='" + details + '\'' +
                ", cost=" + cost +
                ", customerId=" + customerId +
                ", validFrom=" + validFrom +
                ", validUntil=" + validUntil +
                ", creditsRemaining=" + creditsRemaining +
                '}';
    }
}
