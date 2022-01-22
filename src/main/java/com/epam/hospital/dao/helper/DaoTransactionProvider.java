package com.epam.hospital.dao.helper;

import com.epam.hospital.dao.AbstractDao;
import com.epam.hospital.dao.connectionpool.ConnectionPool;
import com.epam.hospital.dao.connectionpool.PooledConnection;
import com.epam.hospital.dao.connectionpool.exception.ConnectionPoolException;
import com.epam.hospital.dao.exception.DaoException;
import com.epam.hospital.model.Entity;

import java.sql.SQLException;

public class DaoTransactionProvider implements AutoCloseable {
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private PooledConnection pooledConnection;

    @SafeVarargs
    public final void initTransaction(AbstractDao<? extends Entity>... daos) throws DaoException {
        if (pooledConnection == null) {
            try {
                pooledConnection = connectionPool.takeConnection();
            } catch (ConnectionPoolException e) {
                throw new DaoException(e);
            }
        }
        if (daos.length > 1) {
            try {
                pooledConnection.setAutoCommit(false);
            } catch (SQLException e) {
//                LOGGER.error("Init transaction error: " + e);
                throw new DaoException(e);
            }
        }
        for (AbstractDao<? extends Entity> daoElement : daos) {
            daoElement.setConnection(pooledConnection);
        }
    }

    public void commit() throws DaoException {
        try {
            pooledConnection.commit();
        } catch (SQLException e) {
//            LOGGER.error("Commit error: " + e);
            throw new DaoException(e);
        }
    }

    public void rollback() {
        try {
            pooledConnection.rollback();
        } catch (SQLException e) {
//            LOGGER.error("Rollback error: " + e);
        }
    }

    @Override
    public void close() {
        try {
            if (!pooledConnection.getAutoCommit()) {
                rollback();
            }
        } catch (SQLException e) {
//            LOGGER.error("Get auto commit error: " + e);
        }
        try {
            pooledConnection.close();
        } catch (SQLException e) {
//            LOGGER.error("Proxy connection error: " + e);
        }
    }
}