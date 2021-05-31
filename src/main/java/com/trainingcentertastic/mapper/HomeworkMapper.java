package com.trainingcentertastic.mapper;

import com.trainingcentertastic.entity.Homework;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HomeworkMapper implements RowMapper<Homework> {
    @Override
    public Homework map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(Homework.ID);
        String taskName = resultSet.getString(Homework.COURSE_NAME);
        String username = resultSet.getString(Homework.USERNAME);
        String courseName = resultSet.getString(Homework.COURSE_NAME);
        String link = resultSet.getString(Homework.LINK);
        int mark = resultSet.getInt(Homework.MARK);
        String review = resultSet.getString(Homework.REVIEW);
        return new Homework(id, taskName, username, courseName, link, mark, review);
    }
}
