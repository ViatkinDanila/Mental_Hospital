package com.epam.mentalhospital.dao.connectionpool;

import com.epam.mentalhospital.dao.connectionpool.exception.ConnectionPoolException;

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;

public class ConnectionPool {
    private static ConnectionPool instance = new ConnectionPool();

    private final String url;
    private final String user;
    private final String password;
    private final String driverName;
    private static BlockingQueue<PooledConnection> allConnections;
    private static BlockingQueue<PooledConnection> engagedConnections;
    private static int poolSize;

    public ConnectionPool() {
        DBResourceManager dbResourceManager = DBResourceManager.getInstance();
        this.driverName = dbResourceManager.getValue(DBParameter.DB_DRIVER);
        this.url = dbResourceManager.getValue(DBParameter.DB_URL);
        this.user = dbResourceManager.getValue(DBParameter.DB_USER);
        this.password = dbResourceManager.getValue(DBParameter.DB_PASSWORD);
        try {
            poolSize = Integer.parseInt(dbResourceManager.getValue(DBParameter.DB_POOL_SIZE));
        } catch (NumberFormatException e) {
            poolSize = 5;
        }
    }

    public void init(String url, String user, String password) throws ConnectionPoolException {
        try {
            Class.forName(driverName);
            allConnections = new ArrayBlockingQueue<>(poolSize);
            engagedConnections = new ArrayBlockingQueue<>(poolSize);
            for (int i = 0; i < poolSize; i++) {
                Connection connection = DriverManager.getConnection(url, user, password);
                allConnections.add(new PooledConnection(connection, this));
            }
        } catch (SQLException e) {
            throw new ConnectionPoolException("SQLException in ConnectionPool", e);
        } catch (ClassNotFoundException e) {
            throw new ConnectionPoolException("Cant find database driver", e);
        }
    }

    public void closeConnection(Connection con, Statement st, ResultSet rs) throws ConnectionPoolException {
        try {
            rs.close();
        } catch (SQLException e) {
            throw new ConnectionPoolException("ResultSet isn't closed.", e);
        }

        try {
            st.close();
        } catch (SQLException e) {
            throw new ConnectionPoolException("Statement isn't closed.", e);
        }

        try {
            con.close();
        } catch (SQLException e) {
            throw new ConnectionPoolException("Connection isn't return to the pool.", e);
        }
    }

    public void closeConnection(Connection con, PreparedStatement st) throws ConnectionPoolException {
        try {
            st.close();
        } catch (SQLException e) {
            throw new ConnectionPoolException("Statement isn't closed.", e);
        }

        try {
            con.close();
        } catch (SQLException e) {
            throw new ConnectionPoolException("Connection isn't return to the pool.", e);
        }
    }

    public void dispose() throws ConnectionPoolException {
        try {
            for (Connection connection : allConnections) {
                connection.close();
            }
            for (Connection connection : engagedConnections) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new ConnectionPoolException("Error closing the connection.", e);
        }
    }


    public PooledConnection takeConnection() throws ConnectionPoolException {
        PooledConnection connection = null;
        try {
            connection = allConnections.take();
            engagedConnections.add(connection);
        } catch (InterruptedException e) {
            throw new ConnectionPoolException("Error connecting to the data source.", e);
        }
        return connection;
    }

    public boolean releaseConnection(PooledConnection connection) throws SQLException {
        if (connection.isClosed()) {
            return false;
        }
        if (!engagedConnections.remove(connection)) {
            return false;
        }
        try {
            allConnections.put(connection);
        } catch (InterruptedException e) {
            throw new SQLException("Error releasing connection.", e);
        }
        return true;
    }

    public static ConnectionPool getInstance() {
        return instance;
    }

}

