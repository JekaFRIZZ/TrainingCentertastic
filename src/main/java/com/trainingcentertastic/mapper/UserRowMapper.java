package com.trainingcentertastic.mapper;

import com.trainingcentertastic.entity.Role;
import com.trainingcentertastic.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User map(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(User.ID);
        String username = resultSet.getString(User.USERNAME);
        String password = resultSet.getString(User.PASSWORD);
        String roleString = resultSet.getString(User.ROLE);
        Role role = Role.valueOf(roleString.toUpperCase());
        return new User(id, username, password, role);
    }
}
