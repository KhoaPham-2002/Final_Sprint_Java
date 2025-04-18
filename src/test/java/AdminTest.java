import org.keyin.memberships.Membership;
import org.keyin.user.User;
import org.keyin.user.childclasses.Admin;


public class AdminTest {
    public static void main(String[] args) {
        System.out.println("----- TEST: Admin Functionality -----");

        // Create admin
        Admin admin = new Admin(1, "admin_user", "admin123", "admin@example.com", "111-222-3333", "Admin Address");

        // Create sample users
        User user1 = new User(2, "member1", "pass1", "member1@example.com", "123-456-7890", "Member Address", "member");
        User user2 = new User(3, "trainer1", "pass2", "trainer1@example.com", "987-654-3210", "Trainer Address", "trainer");

        // Add users
        admin.addUser(user1);
        admin.addUser(user2);

        // View all users
        System.out.println("\n-- Viewing All Users --");
        admin.viewAllUsers();

        // Delete one user
        System.out.println("\n-- Deleting User with ID 2 --");
        admin.deleteUser(2);

        // View all users after deletion
        System.out.println("\n-- Viewing All Users After Deletion --");
        admin.viewAllUsers();

        // Add memberships
        System.out.println("\n-- Adding Memberships --");
        Membership m1 = new Membership(1, "Basic", "Access to gym floor", 29.99, 1001);
        Membership m2 = new Membership(2, "Elite", "All classes + sauna access", 69.99, 1002);
        admin.addMembership(m1);
        admin.addMembership(m2);

        // View memberships and revenue
        System.out.println("\n-- Viewing Memberships and Revenue --");
        admin.viewMembershipsAndRevenue();
    }
}
