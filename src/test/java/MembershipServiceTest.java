import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.keyin.memberships.Membership;
import org.keyin.memberships.MembershipDAO;
import org.keyin.memberships.MembershipService;

public class MembershipServiceTest {
    public static void main(String[] args) {
        try {
            MembershipDAO dao = new MembershipDAO(); // uses the real DAO
            MembershipService service = new MembershipService(dao);

            System.out.println("---- ADDING NEW MEMBERSHIP ----");
            Membership newMembership = new Membership(
                    0,
                    "Silver",
                    "Access to limited features",
                    49.99,
                    2002,
                    LocalDate.of(2024, 5, 1),
                    LocalDate.of(2025, 5, 1),
                    5
            );

            service.addMembership(newMembership);
            System.out.println("Membership added.");

            System.out.println("\n---- GETTING ALL MEMBERSHIPS ----");
            List<Membership> memberships = service.getAllMemberships();
            for (Membership m : memberships) {
                System.out.println(m);
            }

            // Get the ID of the last added membership (assuming it's the last in the list)
            Membership lastAdded = memberships.get(memberships.size() - 1);

            System.out.println("\n---- GETTING MEMBERSHIP BY ID ----");
            Membership fetched = service.getMembershipById(lastAdded.getId());
            System.out.println("Fetched: " + fetched);

            System.out.println("\n---- UPDATING MEMBERSHIP ----");
            fetched.setType("Silver Plus");
            fetched.setCost(59.99);
            service.updateMembership(fetched);
            System.out.println("Updated to: " + service.getMembershipById(fetched.getId()));

            System.out.println("\n---- DELETING MEMBERSHIP ----");
            service.deleteMembership(fetched.getId());
            System.out.println("Deleted membership with ID: " + fetched.getId());

            System.out.println("\n---- FINAL MEMBERSHIP LIST ----");
            List<Membership> finalList = service.getAllMemberships();
            for (Membership m : finalList) {
                System.out.println(m);
            }

        } catch (SQLException e) {
            System.err.println("SQL error occurred: " + e.getMessage());
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.err.println("Validation error: " + e.getMessage());
        }
    }
}
