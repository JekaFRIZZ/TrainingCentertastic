package com.trainingcentertastic.dao;

import com.trainingcentertastic.connetion.ProxyConnection;
import com.trainingcentertastic.exception.DaoException;

import java.sql.SQLException;

public class DaoHelper implements AutoCloseable {
    private final ProxyConnection connection;

    public DaoHelper(ProxyConnection connection) {
        this.connection = connection;
    }
    public UserDao createUserDao() {
        return new UserDao(connection);
    }
    public CourseDao createCourseDao() {
        return new CourseDao(connection);
    }
    public TaskDao createTaskDao() {
        return new TaskDao(connection);
    }
    public HomeworkDao createHomeworkDao() {
        return new HomeworkDao(connection);
    }
    public CourseUsersDao createCourseUsersDao() {return new CourseUsersDao(connection);}

    @Override
    public void close() throws Exception {
        try {
            connection.setAutoCommit(true);
            connection.close();
        } catch (SQLException exception) {
            throw new DaoException(exception.getMessage(), exception);
        }
    }

    public void endTransaction() throws DaoException {
        try {
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException exception) {
            throw new DaoException(exception.getMessage(), exception);
        }
    }

    public void commit() throws DaoException {
        try {
            connection.commit();
        } catch (SQLException exception) {
            throw new DaoException(exception.getMessage(), exception);
        }
    }

    public void rollback() throws DaoException {
        try {
            connection.rollback();
        } catch (SQLException exception) {
            throw new DaoException(exception.getMessage(), exception);
        }
    }

    public void startTransaction() throws DaoException {
        try {
            connection.setAutoCommit(false);
        }catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
