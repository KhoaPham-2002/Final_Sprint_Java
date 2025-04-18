import java.sql.SQLException;

import org.keyin.user.User;
import org.keyin.user.UserDao;

public class UserDaoTest {
    public static void main(String[] args) {
        UserDao userDao = new UserDao();

        try {
            System.out.println("----- TEST: Create User -----");
            User newUser = new User(0, "test_user", "test123", "test@example.com", "123-456-7890", "123 Test St", "member");
            boolean created = userDao.createUser(newUser);
            System.out.println("User created: " + created);

            System.out.println("\n----- TEST: Get User By Username -----");
            User fetchedUser = userDao.getUserByUsername("test_user");
            if (fetchedUser != null) {
                System.out.println("Fetched user: " + fetchedUser);
            } else {
                System.out.println("User not found.");
            }

            System.out.println("\n----- TEST: Update User Password -----");
            if (fetchedUser != null) {
                fetchedUser.setPassword("newpass456");
                userDao.updateUserPassword(fetchedUser);
                System.out.println("Password updated for user ID " + fetchedUser.getId());
            }

            System.out.println("\n----- TEST: Delete User -----");
            if (fetchedUser != null) {
                userDao.deleteUser(fetchedUser.getId());
                System.out.println("User deleted with ID " + fetchedUser.getId());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

