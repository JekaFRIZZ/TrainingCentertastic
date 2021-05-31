package com.trainingcentertastic.entity;

public class Student implements Identifiable {
    public static final String ID = "user_id";
    public static final String USERNAME = "username";
    public static final String REVIEW = "review";

    private Long id;
    private String username;
    private String review;

    public Student(Long id, String username, String review) {
        this.id = id;
        this.username = username;
        this.review = review;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
