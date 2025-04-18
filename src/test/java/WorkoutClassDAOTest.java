import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.keyin.workoutclasses.WorkoutClass;
import org.keyin.workoutclasses.WorkoutClassDAO;

public class WorkoutClassDAOTest {
    public static void main(String[] args) {
        try {
            WorkoutClassDAO dao = new WorkoutClassDAO();

            // Step 1: Create a new WorkoutClass
            WorkoutClass newClass = new WorkoutClass(
                999,  // Temporary ID; won't be used for insertion
                "Pilates",
                "Core strength and flexibility",
                1,
                LocalTime.of(8, 0),
                LocalTime.of(9, 0),
                LocalDate.of(2025, 4, 21)
            );

            System.out.println("Adding new workout class...");
            dao.addClass(newClass); // Note: won't return ID unless modified

            // Step 2: Retrieve all classes and get the last one
            List<WorkoutClass> allClasses = dao.getAllClasses();
            WorkoutClass lastInserted = allClasses.get(allClasses.size() - 1);
            int insertedId = lastInserted.getId();
            System.out.println("Inserted class ID: " + insertedId);
            System.out.println("Retrieved: " + lastInserted);

            // Step 3: Update the class
            lastInserted.setType("Zumba");
            lastInserted.setDetails("Dance cardio workout");
            lastInserted.setInstructorId(2);
            lastInserted.setStartTime(LocalTime.of(10, 0));
            lastInserted.setEndTime(LocalTime.of(11, 0));
            lastInserted.setSessionDate(LocalDate.of(2025, 5, 1));
            dao.updateClass(lastInserted);
            System.out.println("Updated workout class.");

            // Step 4: Get the class again and print updated info
            WorkoutClass updated = dao.getClassById(insertedId);
            System.out.println("Retrieved after update: " + updated);

            // Step 5: Delete the class
            dao.deleteClass(insertedId);
            System.out.println("Deleted workout class.");

            // Step 6: Try to get the class again
            WorkoutClass deleted = dao.getClassById(insertedId);
            System.out.println("Trying to retrieve deleted class: " + deleted);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
