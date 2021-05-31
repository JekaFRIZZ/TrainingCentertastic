package com.trainingcentertastic.mapper;

import com.trainingcentertastic.entity.Role;
import com.trainingcentertastic.entity.Task;
import com.trainingcentertastic.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskMapper implements RowMapper<Task> {
    @Override
    public Task map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(Task.ID);
        String taskName = resultSet.getString(Task.TASK_NAME);
        String courseName = resultSet.getString(Task.COURSE_NAME);
        String assignment = resultSet.getString(Task.ASSIGNMENT);
        return new Task(id, taskName, courseName, assignment);
    }
}
