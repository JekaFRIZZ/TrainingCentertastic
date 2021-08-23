package com.trainingcentertastic.connetion;

import com.mysql.cj.jdbc.Driver;
import com.trainingcentertastic.exception.DaoException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String MYSQL_URL = "jdbc:mysql://localhost:3306/training_centertastic_db?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC";
    private static final String MYSQL_USERNAME = "root";
    private static final String MYSQL_PASSWORD = "admin";

    public ConnectionFactory() throws DaoException {
        try {
            DriverManager.registerDriver(new Driver());
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    Connection create() throws DaoException {
        try {
            return DriverManager.getConnection(MYSQL_URL, MYSQL_USERNAME, MYSQL_PASSWORD);
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }
}
