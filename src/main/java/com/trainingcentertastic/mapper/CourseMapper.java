package com.trainingcentertastic.mapper;

import com.trainingcentertastic.entity.Course;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseMapper implements RowMapper<Course> {
    @Override
    public Course map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(Course.ID);
        String name = resultSet.getString(Course.NAME);
        String requirement = resultSet.getString(Course.REQUIREMENT);
        return new Course(id, name, requirement);
    }
}
