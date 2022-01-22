package com.epam.hospital.dao.queryexecutor;

import com.epam.hospital.dao.exception.DaoException;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public interface QueryExecutor<T> {
    List<T> executeQuery(String query, Object... params) throws DaoException;

    T executeOneEntityQuery(String query, Object... params) throws DaoException;

    void setParams(PreparedStatement statement, Object... params) throws SQLException;

    int executeUpdate(String query, Object... params) throws DaoException;
}
