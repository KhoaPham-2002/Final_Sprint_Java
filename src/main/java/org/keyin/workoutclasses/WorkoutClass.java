package org.keyin.workoutclasses;

import java.time.LocalDate;
import java.time.LocalTime;

public class WorkoutClass {
    private int id;
    private String type;
    private String details;
    private int instructorId;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate sessionDate;

    public WorkoutClass() {
        // Default constructor
    }    

    // Constructor
    public WorkoutClass(int id, String type, String details, int instructorId, LocalTime startTime, LocalTime endTime, LocalDate sessionDate) {
        this.id = id;
        this.type = type;
        this.details = details;
        this.instructorId = instructorId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.sessionDate = sessionDate;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(int instructorId) {
        this.instructorId = instructorId;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public LocalDate getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(LocalDate sessionDate) {
        this.sessionDate = sessionDate;
    }

    @Override
    public String toString() {
        return "WorkoutClass{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", details='" + details + '\'' +
                ", instructorId=" + instructorId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", sessionDate=" + sessionDate +
                '}';
    }
}

