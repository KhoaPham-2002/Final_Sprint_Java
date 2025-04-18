package org.keyin;

import java.time.LocalDate;
import java.time.LocalTime;

import org.keyin.memberships.Membership;
import org.keyin.user.User;
import org.keyin.user.childclasses.Admin;
import org.keyin.user.childclasses.Member;
import org.keyin.user.childclasses.Trainer;
import org.keyin.workoutclasses.WorkoutClass;


public class GymApp {
    public static void main(String[] args) {

        // Register sample users
        GymAppUtils.registerTestUser("john_doe", "john@example.com", "Password123", "123-456-7890", "123 Gym Street", "member");
        GymAppUtils.registerTestUser("jane_smith", "jane@example.com", "Pass456", "111-222-3333", "456 Fit Ave", "member");
        GymAppUtils.registerTestUser("alice_wong", "alice@example.com", "Secure789", "999-888-7777", "789 Flex Blvd", "member");
        GymAppUtils.registerTestUser("bob_lee", "bob@example.com", "Qwerty", "222-333-4444", "321 Muscle Way", "member");
        GymAppUtils.registerTestUser("trainer_mike", "mike@trainer.com", "Number1", "555-666-7777", "22 Coach Lane", "trainer");
        GymAppUtils.registerTestUser("trainer_sara", "sara@trainer.com", "Strongpass", "444-333-2222", "88 Lift Road", "trainer");
        GymAppUtils.registerTestUser("admin_anna", "anna@admin.com", "Adminpower", "101-202-3030", "1 Admin Plaza", "admin");
        // Simulate admin login
        String testUsername = "admin_anna";
        String testPassword = "Adminpower";

        System.out.println("Attempting login for user: " + testUsername);
        GymAppUtils.loginAndPrintUser(testUsername, testPassword);

        // Get the currently logged-in user
        User loggedInUser = GymAppUtils.getLoggedInUser();

        // Check if login was successful
        if (loggedInUser == null) {
            System.out.println("Login failed. Exiting application.");
            return;
        }

        System.out.println("Logged-in user role: " + loggedInUser.getRole());

        // Check if the logged-in user is actually an Admin instance
        if (loggedInUser instanceof Admin) {
            //Admin admin = (Admin) loggedInUser;

            System.out.println("\n--- Admin Functionalities ---");

            // View all users
            System.out.println("\nAll Registered Users:");
            GymAppUtils.displayAllUsers();

            // Delete a user (example: ID 2)
            System.out.println("\nAttempting to delete user with ID 2...");
            GymAppUtils.deleteUserFromSystem( 2);

            // View all memberships
            System.out.println("\nAll Memberships");
            GymAppUtils.printAllMemberships();

            // View revenue
            System.out.println("\nRevenue");
            GymAppUtils.printTotalRevenue();

        } 
        else if (loggedInUser instanceof Member) {
            Member member = (Member) loggedInUser;

            System.out.println("\n--- Member Functionalities ---");

            // Example membership creation (you would replace this with real user input in a real app)
            Membership newMembership = new Membership();
            newMembership.setType("Gold");
            newMembership.setDetails("Full access to gym, classes, and exclusive events.");
            newMembership.setCost(99.99);
            newMembership.setCustomerId(member.getId()); // Use member's ID for customerId
            newMembership.setValidFrom(LocalDate.now());
            newMembership.setValidUntil(LocalDate.now().plusYears(1)); // Set validity to 1 year
            newMembership.setCreditsRemaining(10);

            // Add the new membership for the member
            System.out.println("\nAttempting to add membership...");
            GymAppUtils.addMembership(newMembership);

            newMembership.setCost(120.00);

            // Update the new membership for the member
            System.out.println("\nAttempting to update membership...");
            GymAppUtils.updateMembership(newMembership);

            // Delete the new membership for the member
            System.out.println("\nAttempting to delete membership...");
            GymAppUtils.deleteMembership(1);

            // Print the new membership for the member
            System.out.println("\nAttempting to print all membership...");
            GymAppUtils.printAllMembershipsByCustomerId(1);

            // Print all workout classes
            System.out.println("\nAttempting to print all workoutclassws...");
            GymAppUtils.printAllWorkoutClasses();

        }
        else if (loggedInUser instanceof Trainer){
            Trainer trainer = (Trainer) loggedInUser;

            System.out.println("\n--- Trainer Functionalities ---");

            // Example membership creation 
            Membership newMembership = new Membership();
            newMembership.setType("VIP");
            newMembership.setDetails("Full access to gym and Sauna");
            newMembership.setCost(150);
            newMembership.setCustomerId(trainer.getId()); // Use trainer's ID for customerId
            newMembership.setValidFrom(LocalDate.now());
            newMembership.setValidUntil(LocalDate.now().plusMonths(8)); // Set validity to 1 year
            newMembership.setCreditsRemaining(200);

            // Add the new membership for the trainer
            System.out.println("\nAttempting to add membership...");
            GymAppUtils.addMembership(newMembership);

            newMembership.setCost(190);

            // Update the new membership for the trainer
            System.out.println("\nAttempting to update membership...");
            GymAppUtils.updateMembership(newMembership);

            // Delete the new membership for the trainer
            System.out.println("\nAttempting to delete membership...");
            GymAppUtils.deleteMembership(1);

            // Example workout class creation
            WorkoutClass newWorkoutClass = new WorkoutClass();
            newWorkoutClass.setType("HIIT");
            newWorkoutClass.setDetails("High Intensity Interval Training");
            newWorkoutClass.setInstructorId(trainer.getId()); // Associate with trainer's ID
            newWorkoutClass.setStartTime(LocalTime.of(7, 0)); // 7 AM start
            newWorkoutClass.setEndTime(LocalTime.of(8, 0));   // 8 AM end
            newWorkoutClass.setSessionDate(LocalDate.now().plusDays(1)); // Schedule for tomorrow

            // Add the new workout class
            System.out.println("\nAttempting to add workout class...");
            GymAppUtils.createWorkoutClass(newWorkoutClass);

            // Update the workout class details
            newWorkoutClass.setDetails("Morning HIIT Training with focus on cardio");
            newWorkoutClass.setEndTime(LocalTime.of(8, 30)); // Extend time by 30 mins

            System.out.println("\nAttempting to update workout class...");
            GymAppUtils.updateWorkoutClass(newWorkoutClass);

            // Delete the workout class (use the correct ID if inserted into DB)
            System.out.println("\nAttempting to delete workout class...");
            GymAppUtils.deleteWorkoutClass(1);
        }
         else {
            System.out.println("Access denied.");
        }
    }
}
