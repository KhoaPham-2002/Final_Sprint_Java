package org.keyin.workoutclasses;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import org.keyin.database.DatabaseConnection;

public class WorkoutClassDAO {

    public WorkoutClass getClassById(int id) throws SQLException {
        String sql = "SELECT * FROM training_session WHERE session_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return extractWorkoutClass(rs);
                }
            }
        }
        return null;
    }

    public void getAllClasses() throws SQLException {
        ResultSet rs = null;
        String sql = "SELECT * FROM training_session";
    
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
    
            rs = pstmt.executeQuery();
            System.out.println("Workout Classes in the database:");
    
            while (rs.next()) {
                int sessionId = rs.getInt("session_id");
                String sessionType = rs.getString("session_type");
                String sessionDetails = rs.getString("session_details");
                int instructorId = rs.getInt("instructor_id");
                Time startTime = rs.getTime("starts_at");
                Time endTime = rs.getTime("ends_at");
                Date sessionDate = rs.getDate("session_day");
    
                System.out.println("-------------------------------");
                System.out.println("Session ID: " + sessionId);
                System.out.println("Type: " + sessionType);
                System.out.println("Details: " + sessionDetails);
                System.out.println("Instructor ID: " + instructorId);
                System.out.println("Start Time: " + startTime.toLocalTime());
                System.out.println("End Time: " + endTime.toLocalTime());
                System.out.println("Session Date: " + sessionDate.toLocalDate());
                System.out.println("-------------------------------");
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
    }
    

    public void addClass(WorkoutClass workoutClass) throws SQLException {
        String sql = "INSERT INTO training_session (session_type, session_details, instructor_id, starts_at, ends_at, session_day) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, workoutClass.getType());
            pstmt.setString(2, workoutClass.getDetails());
            pstmt.setInt(3, workoutClass.getInstructorId());
            pstmt.setTime(4, Time.valueOf(workoutClass.getStartTime()));
            pstmt.setTime(5, Time.valueOf(workoutClass.getEndTime()));
            pstmt.setDate(6, Date.valueOf(workoutClass.getSessionDate()));

            pstmt.executeUpdate();
        }
    }

    public void updateClass(WorkoutClass workoutClass) throws SQLException {
        String sql = "UPDATE training_session SET session_type = ?, session_details = ?, instructor_id = ?, starts_at = ?, ends_at = ?, session_day = ? " +
                     "WHERE session_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, workoutClass.getType());
            pstmt.setString(2, workoutClass.getDetails());
            pstmt.setInt(3, workoutClass.getInstructorId());
            pstmt.setTime(4, Time.valueOf(workoutClass.getStartTime()));
            pstmt.setTime(5, Time.valueOf(workoutClass.getEndTime()));
            pstmt.setDate(6, Date.valueOf(workoutClass.getSessionDate()));
            pstmt.setInt(7, workoutClass.getId());

            pstmt.executeUpdate();
        }
    }

    public void deleteClass(int id) throws SQLException {
        String sql = "DELETE FROM training_session WHERE session_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    private WorkoutClass extractWorkoutClass(ResultSet rs) throws SQLException {
        return new WorkoutClass(
                rs.getInt("session_id"),
                rs.getString("session_type"),
                rs.getString("session_details"),
                rs.getInt("instructor_id"),
                rs.getTime("starts_at").toLocalTime(),
                rs.getTime("ends_at").toLocalTime(),
                rs.getDate("session_day").toLocalDate()
        );
    }
}

