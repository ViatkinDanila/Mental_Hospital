package com.epam.hospital.dao.impl;

import com.epam.hospital.dao.UserDao;
import com.epam.hospital.dao.builder.BuilderFactory;
import com.epam.hospital.dao.connectionpool.ConnectionPool;
import com.epam.hospital.dao.connectionpool.exception.ConnectionPoolException;
import com.epam.hospital.dao.exception.DaoException;
import com.epam.hospital.dao.table_names.Column;
import com.epam.hospital.dao.table_names.Table;
import com.epam.hospital.model.user.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl extends AbstractDaoImpl<User> implements UserDao {
    public static final String FIND_BY_EMAIL_QUERY = String.format(
            "SELECT * FROM %s WHERE %s=?",
            Table.USER_TABLE,
            Column.USER_EMAIL
    );
    public static final String SAVE_USER_QUERY = String.format(
            "INSERT INTO %s (%s, %s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?, ?)",
            Table.USER_TABLE,
            Column.USER_FIRS_NAME,
            Column.USER_LAST_NAME,
            Column.USER_NUMBER,
            Column.USER_EMAIL,
            Column.USER_PASWORD,
            Column.USER_ROLE_ID

    );
    public final static String UPDATE_USER_QUERY = String.format(
            "UPDATE %s SET %s=?, %s=?, %s=?, %s=?, %s=?, %s=? WHERE %s=?",
            Table.USER_TABLE,
            Column.USER_FIRS_NAME,
            Column.USER_LAST_NAME,
            Column.USER_NUMBER,
            Column.USER_EMAIL,
            Column.USER_PASWORD,
            Column.USER_ROLE_ID,
            Column.USER_ID
    );
    public final static String FIND_BY_LOGIN_QUERY = String.format(
            "SELECT * FROM %s WHERE %s=?",
            Table.USER_TABLE,
            Column.USER_LOGIN
    );

    public UserDaoImpl() {
        super(BuilderFactory.getUserBuilder(), Table.USER_TABLE, Column.USER_ID);
    }

    @Override
    public void save(User entity) throws DaoException {
        try (//Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = pooledConnection.prepareStatement(SAVE_USER_QUERY)) {
            setParams(entity, statement, SAVE_USER_QUERY);
            statement.executeUpdate();
        } catch (SQLException e) {
//            LOGGER.error("Unable to save new user.",e);
            throw new DaoException(e);
        }
    }

    @Override
    public void update(User entity) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER_QUERY)) {
            setParams(entity, statement, UPDATE_USER_QUERY);
            statement.executeUpdate();
        } catch (SQLException e) {
//            LOGGER.error("Unable to save new user.",e);
            throw new DaoException(e);
        } catch (ConnectionPoolException e) {
//            LOGGER.error("Unable to get connection.",e);
            throw new DaoException("Unable to get connection.", e);
        }
    }

    @Override
    public User findByEmail(String email) throws DaoException {
        User entity = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(FIND_BY_EMAIL_QUERY);
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                entity = BuilderFactory.getUserBuilder().build(resultSet);
            }
        } catch (SQLException e) {
//            LOGGER.error("Can't find entity by id.",e);
            throw new DaoException("Can't find user by email.", e);
        } catch (ConnectionPoolException e) {
//            LOGGER.error("Can't get connection.",e);
            throw new DaoException("Unable to get connection.", e);
        }
        return entity;
    }

    private void setParams(User user, PreparedStatement statement, String action) throws SQLException {
        statement.setString(1, user.getFirstname());
        statement.setString(2, user.getLastname());
        statement.setString(3, user.getNumber());
        statement.setString(4, user.getEmail());
        statement.setString(5, user.getHashedPassword());
        statement.setInt(6, user.getUserRoleId());
        if (action.equals(UPDATE_USER_QUERY)) {
            statement.setInt(7, user.getUserId());
        }
    }
}
