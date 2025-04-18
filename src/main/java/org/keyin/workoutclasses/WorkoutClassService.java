package org.keyin.workoutclasses;

import java.sql.SQLException;

public class WorkoutClassService {

    // Inject DAO
    private WorkoutClassDAO workoutDAO;

    public WorkoutClassService() {
        this.workoutDAO = new WorkoutClassDAO(); // Initialize DAO
    }

    public WorkoutClass getWorkoutClassById(int id) throws SQLException {
        return workoutDAO.getClassById(id);
    }

    public void printAllWorkoutClassesInSystem() throws SQLException {
        workoutDAO.getAllClasses(); 
    }
    

    public void createWorkoutClass(WorkoutClass workoutClass) throws SQLException {
        if (workoutClass.getType() == null || workoutClass.getType().isEmpty()) {
            throw new IllegalArgumentException("Workout class type cannot be empty.");
        }

        workoutDAO.addClass(workoutClass);
    }

    public void updateWorkoutClass(WorkoutClass workoutClass) throws SQLException {
        if (workoutClass.getId() <= 0) {
            throw new IllegalArgumentException("Invalid class ID.");
        }

        workoutDAO.updateClass(workoutClass);
    }

    public void deleteWorkoutClass(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid class ID.");
        }

        workoutDAO.deleteClass(id);
    }
}

