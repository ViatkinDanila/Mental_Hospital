package com.epam.hospital.dao.connectionpool;

import com.epam.hospital.dao.connectionpool.exception.ConnectionPoolException;

import java.sql.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    private static ConnectionPool instance = null;
    private final String driverName;
    private static BlockingQueue<PooledConnection> allConnections;
    private static BlockingQueue<PooledConnection> engagedConnections;
    private static int poolSize;

    private ConnectionPool() {
        DBResourceManager dbResourceManager = DBResourceManager.getInstance();
        this.driverName = dbResourceManager.getValue(DBParameter.DB_DRIVER);
        try {
            poolSize = Integer.parseInt(dbResourceManager.getValue(DBParameter.DB_POOL_SIZE));
        } catch (NumberFormatException e) {
            poolSize = 5;
        }

    }

    public static ConnectionPool getInstance(){
        if (instance == null){
            instance = new ConnectionPool();
        }
        return instance;
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

//    public void closeConnection(Connection con, Statement st, ResultSet rs) throws ConnectionPoolException {
//        try {
//            rs.close();
//        } catch (SQLException e) {
//            throw new ConnectionPoolException("ResultSet isn't closed.", e);
//        }
//
//        try {
//            st.close();
//        } catch (SQLException e) {
//            throw new ConnectionPoolException("Statement isn't closed.", e);
//        }
//
//        try {
//            con.close();
//        } catch (SQLException e) {
//            throw new ConnectionPoolException("Connection isn't return to the pool.", e);
//        }
//    }
//
//    public void closeConnection(Connection con, PreparedStatement st) throws ConnectionPoolException {
//        try {
//            st.close();
//        } catch (SQLException e) {
//            throw new ConnectionPoolException("Statement isn't closed.", e);
//        }
//
//        try {
//            con.close();
//        } catch (SQLException e) {
//            throw new ConnectionPoolException("Connection isn't return to the pool.", e);
//        }
//    }

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


}

