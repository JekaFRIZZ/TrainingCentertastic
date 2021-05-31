package com.trainingcentertastic.entity;

public class Task implements Identifiable {
    public static final String ID = "id";
    public static final String TASK_NAME = "task_name";
    public static final String COURSE_NAME = "course_name";
    public static final String ASSIGNMENT = "assignment";

    private Long id;
    private String taskName;
    private String courseName;
    private String assignment;

    public Task(Long id, String taskName, String courseName, String assignment) {
        this.id = id;
        this.taskName = taskName;
        this.courseName = courseName;
        this.assignment = assignment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getAssignment() {
        return assignment;
    }

    public void setAssignment(String assignment) {
        this.assignment = assignment;
    }
}
