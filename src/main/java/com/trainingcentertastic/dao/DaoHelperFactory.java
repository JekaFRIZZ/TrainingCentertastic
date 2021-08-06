package com.trainingcentertastic.dao;

import com.trainingcentertastic.connetion.ConnectionPool;

public class DaoHelperFactory {
    public DaoHelper create() {
        return new DaoHelper(ConnectionPool.getInstance());
    }
}
