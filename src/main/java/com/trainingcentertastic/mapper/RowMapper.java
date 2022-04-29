package com.trainingcentertastic.mapper;

import com.trainingcentertastic.entity.Identifiable;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * An interface {@code RowMapper} that allows you to convert data from a database to entity class
 *
 * @author Eugene Lapko
 */
public interface RowMapper<T extends Identifiable> {

    String USER = "user";
    String TASK = "task";
    String HOMEWORK = "homework";
    String COURSE = "course";


    static RowMapper<? extends Identifiable> create(String table) {
        switch (table) {
            case USER:
                return new UserMapper();
            case TASK:
                return new TaskMapper();
            case COURSE:
                return new CourseMapper();
            default:
                throw new IllegalArgumentException("Unknown table" + table);
        }
    }

    /**
     * Get data from the database in the form of a ResultSet and creates entity class
     *
     * @param resultSet database query result
     * @return an entity class inherited from Identifiable{@code Identifiable}
     * @throws SQLException if there is no database access
     */
    T map(ResultSet resultSet) throws SQLException;
}
