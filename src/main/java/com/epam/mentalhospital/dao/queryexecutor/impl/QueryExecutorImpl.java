package com.epam.mentalhospital.dao.queryexecutor.impl;

import com.epam.mentalhospital.dao.builder.EntityBuilder;
import com.epam.mentalhospital.dao.connectionpool.ConnectionPool;
import com.epam.mentalhospital.dao.connectionpool.exception.ConnectionPoolException;
import com.epam.mentalhospital.dao.exception.DaoException;
import com.epam.mentalhospital.dao.queryexecutor.QueryExecutor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QueryExecutorImpl<T> implements QueryExecutor<T> {
    private final EntityBuilder<T> builder;

    public QueryExecutorImpl(EntityBuilder<T> builder) {
        this.builder = builder;
    }

    @Override
    public List<T> executeQuery(String query, Object... params) throws DaoException {
        List<T> result = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            setParams(statement, params);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                T entity = builder.build(resultSet);
                result.add(entity);
            }
        } catch (SQLException e) {
            throw new DaoException("Cannot to execute query.", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("Cannot to get connection.", e);
        }
        return result;
    }

    @Override
    public T executeOneEntityQuery(String query, Object... params) throws DaoException {
        T enetity = null;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            setParams(statement, params);
            ResultSet resultSet = statement.executeQuery();
            enetity = builder.build(resultSet);
        } catch (SQLException e) {
            throw new DaoException("Cannot to execute query.", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("Cannot to get connection.", e);
        }
        return enetity;
    }

    @Override
    public void setParams(PreparedStatement statement, Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            statement.setObject(i + 1, params[i]);
        }
    }

    @Override
    public int executeUpdate(String query, Object... params) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            setParams(statement, params);
            int rowsAffected = statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys != null && generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                return rowsAffected;
            }
        } catch (SQLException e) {
            throw new DaoException("Cannot to execute query.", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("Cannot to get connection.", e);
        }
    }
}
