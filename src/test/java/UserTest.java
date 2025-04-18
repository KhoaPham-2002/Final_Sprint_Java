import org.keyin.user.User;

public class UserTest {
    public static void main(String[] args) {
        // Create a user
        User user = new User(
                1,
                "john_doe",
                "securePassword123",
                "john@example.com",
                "123-456-7890",
                "123 Main St",
                "Member"
        );

        System.out.println("---- USER CREATED ----");
        System.out.println(user);

        // Test getters
        System.out.println("\n---- GETTING USER INFO ----");
        System.out.println("Username: " + user.getUsername());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Phone: " + user.getPhone());
        System.out.println("Address: " + user.getAddress());
        System.out.println("Role: " + user.getRole());

        // Test setters
        System.out.println("\n---- UPDATING USER INFO ----");
        user.setUsername("jane_doe");
        user.setEmail("jane@example.com");
        user.setPhone("098-765-4321");
        user.setAddress("456 Elm St");
        user.setRole("Admin");

        System.out.println("Updated User: ");
        System.out.println(user);

        // Check password update (demonstrate it's settable, not printed)
        user.setPassword("newSecurePassword!");
        System.out.println("\nPassword updated successfully (not displayed for security).");
    }
}

