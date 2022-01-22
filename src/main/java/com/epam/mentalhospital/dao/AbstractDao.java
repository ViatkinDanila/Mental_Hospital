package com.epam.mentalhospital.dao;

import com.epam.mentalhospital.dao.connectionpool.PooledConnection;
import com.epam.mentalhospital.dao.exception.DaoException;
import com.epam.mentalhospital.model.Entity;

import java.util.List;

public interface AbstractDao<T extends Entity> {
    void save(T entity) throws DaoException;

    void update(T entity) throws DaoException;

    void deleteById(int id) throws DaoException;

    T findById(int id) throws DaoException;

    List<T> findAll() throws DaoException;

    void setConnection(PooledConnection pooledConnection);
}
