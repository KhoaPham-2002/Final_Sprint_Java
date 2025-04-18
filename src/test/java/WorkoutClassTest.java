import java.time.LocalDate;
import java.time.LocalTime;

import org.keyin.workoutclasses.WorkoutClass;

public class WorkoutClassTest {
    public static void main(String[] args) {
        // Create a WorkoutClass object
        WorkoutClass workout = new WorkoutClass(
                1,
                "Yoga",
                "Relaxing yoga session",
                101,
                LocalTime.of(9, 0),
                LocalTime.of(10, 0),
                LocalDate.of(2025, 4, 20)
        );

        // Print original values
        System.out.println("Original WorkoutClass values:");
        System.out.println("ID: " + workout.getId());
        System.out.println("Type: " + workout.getType());
        System.out.println("Details: " + workout.getDetails());
        System.out.println("Instructor ID: " + workout.getInstructorId());
        System.out.println("Start Time: " + workout.getStartTime());
        System.out.println("End Time: " + workout.getEndTime());
        System.out.println("Session Date: " + workout.getSessionDate());

        // Modify values using setters
        workout.setType("HIIT");
        workout.setDetails("High intensity interval training");
        workout.setInstructorId(202);
        workout.setStartTime(LocalTime.of(17, 30));
        workout.setEndTime(LocalTime.of(18, 30));
        workout.setSessionDate(LocalDate.of(2025, 5, 1));

        // Print updated values
        System.out.println("\nUpdated WorkoutClass values:");
        System.out.println("Type: " + workout.getType());
        System.out.println("Details: " + workout.getDetails());
        System.out.println("Instructor ID: " + workout.getInstructorId());
        System.out.println("Start Time: " + workout.getStartTime());
        System.out.println("End Time: " + workout.getEndTime());
        System.out.println("Session Date: " + workout.getSessionDate());

        // Print toString output
        System.out.println("\nWorkoutClass toString output:");
        System.out.println(workout.toString());
    }
}
