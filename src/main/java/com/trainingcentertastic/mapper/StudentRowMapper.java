package com.trainingcentertastic.mapper;

import com.trainingcentertastic.entity.Student;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRowMapper implements RowMapper<Student> {
    @Override
    public Student map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(Student.ID);
        String username = resultSet.getString(Student.USERNAME);
        String review = resultSet.getString(Student.REVIEW);
        return new Student(id, username, review);
    }
}
