package com.epam.hospital.dao.impl;

import com.epam.hospital.dao.UserDao;
import com.epam.hospital.dao.builder.BuilderFactory;
import com.epam.hospital.dao.connectionpool.ConnectionPool;
import com.epam.hospital.dao.connectionpool.exception.ConnectionPoolException;
import com.epam.hospital.dao.exception.DaoException;
import com.epam.hospital.dao.table_names.Column;
import com.epam.hospital.dao.table_names.Table;
import com.epam.hospital.model.treatment.Consultation;
import com.epam.hospital.model.user.User;
import com.epam.hospital.model.user.info.DoctorInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends AbstractDaoImpl<User> implements UserDao {
    public static final String SAVE_USER_QUERY = String.format(
            "INSERT INTO %s (%s, %s, %s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?, ?, ?)",
            Table.USER_TABLE,
            Column.USER_FIRS_NAME,
            Column.USER_LAST_NAME,
            Column.USER_NUMBER,
            Column.USER_EMAIL,
            Column.USER_PASWORD,
            Column.USER_ROLE_ID,
            Column.USER_IS_BANNED

    );
    public final static String UPDATE_USER_QUERY = String.format(
            "UPDATE %s SET %s=?, %s=?, %s=?, %s=?, %s=?, %s=?, %s=? WHERE %s=?",
            Table.USER_TABLE,
            Column.USER_FIRS_NAME,
            Column.USER_LAST_NAME,
            Column.USER_NUMBER,
            Column.USER_EMAIL,
            Column.USER_PASWORD,
            Column.USER_ROLE_ID,
            Column.USER_IS_BANNED,
            Column.USER_ID
    );
    public final static String FIND_BY_FULLNAME_QUERY = String.format(
            "SELECT * FROM %s WHERE %s=? AND %s=?",
            Table.USER_TABLE,
            Column.USER_FIRS_NAME,
            Column.USER_LAST_NAME
    );
    public final static String LOGIN_QUERY = String.format(
            "SELECT * FROM %s WHERE %s=? AND %s=?",
            Table.USER_TABLE,
            Column.USER_EMAIL,
            Column.USER_PASWORD
    );
    public final static String FIND_ROLE_BY_ID_QUERY = String.format(
            "SELECT * FROM %s WHERE %s=?",
            Table.USER_ROLES_TABLE,
            Column.USER_ROLES_ID
    );
    public final static String IS_USER_EXIST_QUERY = String.format(
            "SELECT * FROM %s WHERE %s=?",
            Table.USER_ROLES_TABLE,
            Column.USER_EMAIL
    );
    public final static String FIND_DOCTOR_INFO_BY_ID_QUERY = String.format(
            "SELECT * FROM %s WHERE %s=?",
            Table.DOCTOR_INFO_TABLE,
            Column.DOCTOR_INFO_ID
    );

    public UserDaoImpl() {
        super(BuilderFactory.getUserBuilder(), Table.USER_TABLE, Column.USER_ID);
    }

    @Override
    public void save(User entity) throws DaoException {
        try (PreparedStatement statement = pooledConnection.prepareStatement(SAVE_USER_QUERY);) {
            setParams(statement, entity, SAVE_USER_QUERY);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Can't save user.", e);
        }
    }

    @Override
    public void update(User entity) throws DaoException {
        try (PreparedStatement statement = pooledConnection.prepareStatement(UPDATE_USER_QUERY);) {
            setParams(statement, entity, UPDATE_USER_QUERY);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Can't update user.", e);
        }
    }

    @Override
    public User findByEmail(String email) throws DaoException {
        return findByField(Column.USER_EMAIL,email).get(0);
    }
//tested
    public User findByFullName(String firstName, String lastName) throws DaoException{
        User user = new User();
        try (PreparedStatement statement = pooledConnection.prepareStatement(FIND_BY_FULLNAME_QUERY);) {
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = BuilderFactory.getUserBuilder().build(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException("Can't find user by full name.", e);
        }
        return user;
    }

    @Override
    public User findByEmailPassword(String email, String password) throws DaoException {
        User user = new User();
        try (PreparedStatement statement = pooledConnection.prepareStatement(LOGIN_QUERY);) {
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = BuilderFactory.getUserBuilder().build(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException("Can't find user by email and password.", e);
        }
        return user;
    }

    @Override
    public String findUserRole(int id) throws DaoException {
        try (PreparedStatement statement = pooledConnection.prepareStatement(FIND_ROLE_BY_ID_QUERY);) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString(Column.USER_ROLES_NAME);
            }
        } catch (SQLException e) {
            throw new DaoException("Can't find user by email and password.", e);
        }
        return null;
    }

    @Override
    public boolean isUserExist(String login) throws DaoException {
        try (PreparedStatement statement = pooledConnection.prepareStatement(IS_USER_EXIST_QUERY);) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return false;
            }
        } catch (SQLException e) {
            throw new DaoException("Can't find user by email and password.", e);
        }
        return true;
    }

    @Override
    public DoctorInfo findDoctorInfoById(int id) throws DaoException {
        DoctorInfo doctorInfo = new DoctorInfo();
        try (PreparedStatement statement = pooledConnection.prepareStatement(FIND_DOCTOR_INFO_BY_ID_QUERY);) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                doctorInfo = BuilderFactory.getDoctorInfoBuilder().build(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException("Can't find doctor info by doctor id.", e);
        }
        return doctorInfo;
    }

    private void setParams(PreparedStatement statement, User user, String action) throws SQLException {
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
