import java.time.LocalDate;
import java.util.List;

import org.keyin.memberships.Membership;
import org.keyin.memberships.MembershipDAO;

public class MembershipDAOTest {
    public static void main(String[] args) {
        MembershipDAO dao = new MembershipDAO();

        try {
            System.out.println("---- ADDING NEW MEMBERSHIP ----");
            Membership newMembership = new Membership(
                    0, // ID will be auto-generated
                    "Gold",
                    "Access to all basic and premium features",
                    149.99,
                    2001, // Example customer ID
                    LocalDate.of(2024, 4, 1),
                    LocalDate.of(2025, 4, 1),
                    10
            );
            dao.addMembership(newMembership);
            System.out.println("Added membership (manually check DB to see ID if needed)");

            System.out.println("\n---- GETTING ALL MEMBERSHIPS ----");
            List<Membership> memberships = dao.getAllMemberships();
            for (Membership m : memberships) {
                System.out.println(m);
            }

            if (!memberships.isEmpty()) {
                Membership latest = memberships.get(memberships.size() - 1); // Assume last added is latest
                int id = latest.getId();

                System.out.println("\n---- GETTING MEMBERSHIP BY ID ----");
                Membership retrieved = dao.getMembershipById(id);
                System.out.println("Retrieved: " + retrieved);

                System.out.println("\n---- UPDATING MEMBERSHIP ----");
                retrieved.setType("Platinum");
                retrieved.setCost(199.99);
                dao.updateMembership(retrieved);
                System.out.println("Updated membership to: " + dao.getMembershipById(id));

                System.out.println("\n---- DELETING MEMBERSHIP ----");
                dao.deleteMembership(id);
                System.out.println("Deleted membership with ID: " + id);

                System.out.println("\n---- FINAL MEMBERSHIP LIST ----");
                memberships = dao.getAllMemberships();
                for (Membership m : memberships) {
                    System.out.println(m);
                }
            } else {
                System.out.println("No memberships found to test further actions.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

