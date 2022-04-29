package com.trainingcentertastic.entity;

public class Homework implements Identifiable {

    public static final String ID = "id";
    public static final String TASK_NAME = "task_name";
    public static final String USERNAME = "username";
    public static final String COURSE_NAME = "course_name";
    public static final String LINK = "link";
    public static final String MARK = "mark";
    public static final String REVIEW = "review";

    private Long id;
    private String taskName;
    private String username;
    private String courseName;
    private String link;
    private int mark;
    private String review;

    public Homework(Long id, String taskName, String username, String courseName, String link, int mark, String review) {
        this.id = id;
        this.taskName = taskName;
        this.username = username;
        this.courseName = courseName;
        this.link = link;
        this.mark = mark;
        this.review = review;
    }

    public Homework(String taskName, String username, String courseName) {
        this.taskName = taskName;
        this.username = username;
        this.courseName = courseName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
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

    public Long getId() {
        return id;
    }

    public void setTaskId(Long taskId) {
        this.id = taskId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
