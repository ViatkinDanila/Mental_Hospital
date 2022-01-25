package com.epam.hospital.dao;

import com.epam.hospital.dao.exception.DaoException;
import com.epam.hospital.model.user.User;

public interface UserDao extends AbstractDao<User> {
    User findByEmail(String email) throws DaoException;
    User findByFullName(String firstName, String lastName) throws DaoException;
    User findByEmailPassword(String email, String password) throws DaoException;
    String findUserRole(int id) throws DaoException;
}
