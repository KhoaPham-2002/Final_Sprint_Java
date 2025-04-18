import java.time.LocalDate;

import org.keyin.memberships.Membership;

public class MembershipTest {
    public static void main(String[] args) {
        // Create a Membership object with some test data
        Membership membership = new Membership(
                1, "Elite", "Full access plan", 99.99,
                101, LocalDate.of(2024, 1, 1),
                LocalDate.of(2025, 1, 1), 5
        );

        // Testing Getters
        System.out.println("---- Testing Getters ----");
        System.out.println("ID: " + membership.getId());
        System.out.println("Type: " + membership.getType());
        System.out.println("Cost: " + membership.getCost());
        System.out.println("Credits Remaining: " + membership.getCreditsRemaining());

        // Testing isPremiumTier()
        System.out.println("\n---- Testing isPremiumTier() ----");
        System.out.println("Is Premium: " + membership.isPremiumTier());
        membership.setType("Basic");
        System.out.println("Is Premium after change to 'Basic': " + membership.isPremiumTier());

        // Testing deductCredit()
        System.out.println("\n---- Testing deductCredit() ----");
        membership.setCreditsRemaining(2);
        boolean deductedOnce = membership.deductCredit(); // Should return true
        boolean deductedTwice = membership.deductCredit(); // Should return true
        boolean deductedThird = membership.deductCredit(); // Should return false

        System.out.println("Deduct 1st time: " + deductedOnce);
        System.out.println("Deduct 2nd time: " + deductedTwice);
        System.out.println("Deduct when zero: " + deductedThird);

        // Testing toString()
        System.out.println("\n---- Testing toString() ----");
        System.out.println(membership.toString());
    }
}
