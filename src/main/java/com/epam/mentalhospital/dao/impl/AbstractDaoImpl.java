package com.epam.mentalhospital.dao.impl;

import com.epam.mentalhospital.dao.AbstractDao;
import com.epam.mentalhospital.dao.builder.EntityBuilder;
import com.epam.mentalhospital.dao.connectionpool.ConnectionPool;
import com.epam.mentalhospital.dao.connectionpool.PooledConnection;
import com.epam.mentalhospital.dao.connectionpool.exception.ConnectionPoolException;
import com.epam.mentalhospital.dao.exception.DaoException;
import com.epam.mentalhospital.model.Entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDaoImpl<T extends Entity> implements AbstractDao<T> {
    private EntityBuilder<T> builder;
    public PooledConnection pooledConnection;
    //    protected final Logger LOGGER = LogManager.getLogger();
    private final String FIND_ALL_QUERY;
    private final String FIND_BY_ID_QUERY;
    private final String DELETE_BY_ID_QUERY;
    private final String FIND_BY_FIELD_QUERY;

    public AbstractDaoImpl(EntityBuilder<T> builder, String tableName, String idName) {
        this.builder = builder;
        FIND_ALL_QUERY = "SELECT * FROM " + tableName;
        FIND_BY_FIELD_QUERY = "SELECT * FROM " + tableName + " WHERE %s=?";
        FIND_BY_ID_QUERY = "SELECT * FROM " + tableName + " WHERE " + idName + "=?";
        DELETE_BY_ID_QUERY = "DELETE FROM " + tableName + " WHERE " + idName + "=?";
    }

    @Override
    public void deleteById(int id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID_QUERY)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
//            LOGGER.error("Can't delete entity by id.",e);
            throw new DaoException("Can't delete entity by id.", e);
        } catch (ConnectionPoolException e) {
//            LOGGER.error("Can't get connection.",e);
            throw new DaoException("Unable to get connection.", e);
        }
    }

    @Override
    public T findById(int id) throws DaoException {
        T entity = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(FIND_BY_ID_QUERY);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                entity = builder.build(resultSet);
            }
        } catch (SQLException e) {
//            LOGGER.error("Can't find entity by id.",e);
            throw new DaoException("Can't find by id.", e);
        } catch (ConnectionPoolException e) {
//            LOGGER.error("Can't get connection.",e);
            throw new DaoException("Unable to get connection.", e);
        }
        return entity;
    }

    public T findByField(String fieldName, Object value) throws DaoException {
        T entity = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            String query = String.format(
                    FIND_BY_FIELD_QUERY, fieldName
            );
            statement = connection.prepareStatement(query);
            statement.setObject(1, value);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                entity = builder.build(resultSet);
            }
        } catch (SQLException e) {
//            LOGGER.error("Can't find entity by id.",e);
            throw new DaoException("Can't find by id.", e);
        } catch (ConnectionPoolException e) {
//            LOGGER.error("Can't get connection.",e);
            throw new DaoException("Unable to get connection.", e);
        }
        return entity;
    }

    @Override
    public List<T> findAll() throws DaoException {
        List<T> result = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_QUERY);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                T entity = builder.build(resultSet);
                result.add(entity);
            }
        } catch (SQLException e) {
//            LOGGER.error("Can't find all entity's.",e);
            throw new DaoException("Can't find all entity's.", e);
        } catch (ConnectionPoolException e) {
//            LOGGER.error("Can't get connection.",e);
            throw new DaoException("Unable to get connection.", e);
        }
        return result;
    }

    @Override
    public void setConnection(PooledConnection pooledConnection) {
        this.pooledConnection = pooledConnection;
    }

}
