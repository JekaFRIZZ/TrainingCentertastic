package com.trainingcentertastic.connetion;

import org.apache.log4j.Logger;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class ConnectionPool {
    private static final Logger LOGGER = Logger.getLogger(ConnectionPool.class);

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
            } finally {
                INSTANCE_LOCK.unlock();
            }
        }
        return INSTANCE.get();
    }

    public ProxyConnection getConnection() {
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
        LOGGER.info("Connection has been taken");
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
        LOGGER.info("Connection pool has been return");
    }
}
