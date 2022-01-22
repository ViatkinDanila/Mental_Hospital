package com.epam.mentalhospital.dao;

import com.epam.mentalhospital.dao.exception.DaoException;
import com.epam.mentalhospital.model.user.User;

public interface UserDao extends AbstractDao<User> {
    User findByEmail(String email) throws DaoException;
}
