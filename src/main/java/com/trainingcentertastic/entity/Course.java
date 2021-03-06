package com.trainingcentertastic.entity;

public class Course implements Identifiable {
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String REQUIREMENT = "requirement";

    private Long id;
    private String name;
    private String requirement;
    private String username;

    public Course() {
    }

    public Course(Long id, String name, String requirement) {
        this.id = id;
        this.name = name;
        this.requirement = requirement;
    }

    public Course(String name, String requirement, String username) {
        this.name = name;
        this.requirement = requirement;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Long getId() {
        return id;
    }
}
