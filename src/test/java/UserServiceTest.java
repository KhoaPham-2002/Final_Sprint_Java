import org.keyin.user.User;
import org.keyin.user.UserService;

public class UserServiceTest {
    public static void main(String[] args) {
        UserService userService = new UserService();

        System.out.println("----- TEST: Register User -----");
        boolean isRegistered = userService.registerUser(
                "test_user",
                "password123",
                "test@example.com",
                "123-456-7890",
                "123 Test St",
                "member"
        );
        System.out.println("User registered: " + isRegistered);

        System.out.println("\n----- TEST: Register Duplicate User -----");
        boolean duplicateRegister = userService.registerUser(
                "test_user",  // same username
                "newpassword",
                "test@example.com",  // same email
                "999-999-9999",
                "New Address",
                "member"
        );
        System.out.println("Duplicate user registered: " + duplicateRegister);

        System.out.println("\n----- TEST: Authenticate (Correct Password) -----");
        boolean authSuccess = userService.authenticateUser("test_user", "password123");
        System.out.println("Authentication success: " + authSuccess);

        System.out.println("\n----- TEST: Authenticate (Wrong Password) -----");
        boolean authFail = userService.authenticateUser("test_user", "wrongpassword");
        System.out.println("Authentication success with wrong password: " + authFail);

        System.out.println("\n----- TEST: Get User By Username -----");
        User user = userService.getUserByUsername("test_user");
        if (user != null) {
            System.out.println("Fetched user: " + user);
        } else {
            System.out.println("User not found.");
        }
    }
}

