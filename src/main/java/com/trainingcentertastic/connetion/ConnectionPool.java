package com.trainingcentertastic.connetion;

import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class ConnectionPool {
    private BlockingQueue<ProxyConnection> availableConnections;
    private Set<ProxyConnection> connectionsInUse;
    private int poolSize;

    private static final AtomicReference<ConnectionPool> INSTANCE = new AtomicReference<>();
    private static final Lock INSTANCE_LOCK = new ReentrantLock();
    private static final Lock CONNECTIONS_LOCK = new ReentrantLock();

    ConnectionPool(int poolSize, List<ProxyConnection> connections) {
        this.poolSize = poolSize;
        this.connectionsInUse = new HashSet<>();
        this.availableConnections = new ArrayBlockingQueue<>(poolSize);
        List<ProxyConnection> updatedConnections = connections.stream()
                .peek(connection -> connection.setConnectionPool(this))
                .collect(Collectors.toList());
        availableConnections.addAll(updatedConnections);
    }

    public static ConnectionPool getInstance() throws ConnectionException {
        if (INSTANCE.get() == null) {
            try {
                INSTANCE_LOCK.lock();
                if (INSTANCE.get() == null) {
                    ConnectionPoolFactory factory = new ConnectionPoolFactory();
                    ConnectionPool connectionPool = factory.createPool();
                    INSTANCE.getAndSet(connectionPool);
                }
            } catch (ConnectionException exception) {
                //change on logger
                throw new ConnectionException(exception);
            } finally {
                INSTANCE_LOCK.unlock();
            }
        }
        return INSTANCE.get();
    }

    public ProxyConnection getConnection() throws ConnectionException {
        ProxyConnection connection = null;
        CONNECTIONS_LOCK.lock();
        try {
            connection = availableConnections.remove();
            connectionsInUse.add(connection);
        } catch (NoSuchElementException exception) {
            throw new ConnectionException(exception.getMessage(), exception);
        } finally {
            CONNECTIONS_LOCK.unlock();
        }
        return connection;
    }

    public void returnConnection(ProxyConnection proxyConnection) {
        CONNECTIONS_LOCK.lock();
        try {
            if (connectionsInUse.contains(proxyConnection)) {
                connectionsInUse.remove(proxyConnection);
                availableConnections.offer(proxyConnection);
            }
        } finally {
            CONNECTIONS_LOCK.unlock();
        }
    }

    public void closeAllConnections() throws ConnectionException {
        for (int i = 0; i < poolSize; i++) {
            try {
                ProxyConnection connection = availableConnections.poll();
                connection.close();
            } catch (SQLException exception) {
                throw new ConnectionException(exception.getMessage(), exception);
            }
        }
        try {
            ConnectionPoolFactory.deregisterDriver();
        } catch (SQLException exception) {
            throw new ConnectionException(exception.getMessage(), exception);
        }
    }
}
