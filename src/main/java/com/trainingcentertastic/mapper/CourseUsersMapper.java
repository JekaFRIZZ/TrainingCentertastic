package com.trainingcentertastic.mapper;

import com.trainingcentertastic.entity.Course;
import com.trainingcentertastic.entity.CourseUsers;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseUsersMapper implements RowMapper<CourseUsers> {
    @Override
    public CourseUsers map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(CourseUsers.ID);
        String username = resultSet.getString(CourseUsers.USERNAME);
        String courseName = resultSet.getString(CourseUsers.COURSE_NAME);
        return new CourseUsers(id, courseName, username);
    }
}
