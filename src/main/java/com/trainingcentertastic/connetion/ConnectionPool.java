package com.trainingcentertastic.connetion;

import com.trainingcentertastic.exception.DaoException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static final Logger LOGGER = Logger.getLogger(ConnectionPool.class);

    private static final int POOL_SIZE = 6;

    private final Semaphore connectionSemaphore = new Semaphore(POOL_SIZE);
    private final Queue<ProxyConnection> availableConnections;
    private final Queue<ProxyConnection> connectionsInUse;
    private final ConnectionFactory connectionFactory;

    private final static AtomicReference<ConnectionPool> INSTANCE = new AtomicReference<>();
    private final static Lock LOCK = new ReentrantLock();

    private ConnectionPool() throws DaoException {
        connectionFactory = new ConnectionFactory();
        connectionsInUse = new ArrayDeque<>();
        availableConnections = new ArrayDeque<>();
        createConnections();
    }

    private void createConnections() throws DaoException {
        for(int i = 0; i < POOL_SIZE; i++) {
            Connection connection = connectionFactory.create();
            ProxyConnection proxyConnection = new ProxyConnection(connection, this);
            availableConnections.add(proxyConnection);
        }
    }

    public static ConnectionPool getInstance() {
        ConnectionPool methodInstance = INSTANCE.get();
        if (methodInstance == null) {
            try {
                LOCK.lock();
                methodInstance = INSTANCE.get();
                if (methodInstance == null) {
                    ConnectionPool connectionPool = new ConnectionPool();
                    INSTANCE.getAndSet(connectionPool);
                }
            } catch (DaoException e) {
                throw new ConnectionException(e.getMessage(), e);
            } finally {
                LOCK.unlock();
            }
        }
        return INSTANCE.get();
    }

    public ProxyConnection getConnection() {
        try {
            connectionSemaphore.acquire();
            LOCK.lock();
            ProxyConnection connection = availableConnections.poll();
            connectionsInUse.add(connection);
            LOGGER.info("Connection has been taken");
            return connection;
        } catch (InterruptedException e) {
            throw new RuntimeException(e.getMessage(), e);
        } finally {
            LOCK.unlock();
        }
    }

    public void returnConnection(ProxyConnection proxyConnection) {
        try {
        LOCK.lock();
            if (connectionsInUse.contains(proxyConnection)) {
                availableConnections.offer(proxyConnection);
                connectionsInUse.remove(proxyConnection);
                connectionSemaphore.release();
            }
        } finally {
            LOCK.unlock();
        }
        LOGGER.info("Connection pool has been return");
    }

    public void closeAllConnection() {
        for(ProxyConnection proxyConnection : connectionsInUse){
            Connection connection = proxyConnection.getConnection();
            try {
                connection.close();
            } catch (SQLException e) {
                throw new ConnectionException(e.getMessage(), e);
            }
        }
    }
}
