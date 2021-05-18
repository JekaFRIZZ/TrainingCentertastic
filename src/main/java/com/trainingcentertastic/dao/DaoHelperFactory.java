package com.trainingcentertastic.dao;

import com.trainingcentertastic.connetion.ConnectionException;
import com.trainingcentertastic.connetion.ConnectionPool;

public class DaoHelperFactory {

    private final ConnectionPool pool = ConnectionPool.getInstance();

    public DaoHelperFactory() throws ConnectionException {
    }

    public DaoHelper create() throws ConnectionException {
        return new DaoHelper(pool.getConnection());
    }
}
