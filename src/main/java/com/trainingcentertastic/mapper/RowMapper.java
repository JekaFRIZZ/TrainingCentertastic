package com.trainingcentertastic.mapper;

import com.trainingcentertastic.entity.Identifiable;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T extends Identifiable> {

    String USER = "user";

    static RowMapper<? extends Identifiable> create(String table) {
        switch (table) {
            case USER:
                return new UserRowMapper();
            default:
                throw new IllegalArgumentException("Unknown table" + table);
        }
    }

    T map(ResultSet resultSet) throws SQLException;
}
