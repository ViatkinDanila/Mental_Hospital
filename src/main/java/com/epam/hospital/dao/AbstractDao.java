package com.epam.hospital.dao;

import com.epam.hospital.dao.connectionpool.PooledConnection;
import com.epam.hospital.dao.exception.DaoException;
import com.epam.hospital.model.Entity;

import java.util.List;

public interface AbstractDao<T extends Entity> {
    void save(T entity) throws DaoException;

    void update(T entity) throws DaoException;

    void deleteById(int id) throws DaoException;

    T findById(int id) throws DaoException;

    List<T> findAll() throws DaoException;

    void setConnection(PooledConnection pooledConnection);
}
