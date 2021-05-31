package com.trainingcentertastic.entity;

public class CourseUsers implements Identifiable {

    public static final String ID = "id";
    public static final String USERNAME = "username";
    public static final String COURSE_NAME = "course_name";

    private Long id;
    private String courseName;
    private String username;

    public CourseUsers(Long id, String courseName, String username) {
        this.id = id;
        this.courseName = courseName;
        this.username = username;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
