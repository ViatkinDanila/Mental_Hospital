package com.epam.hospital.dao;

import com.epam.hospital.dao.exception.DaoException;
import com.epam.hospital.model.user.User;
import com.epam.hospital.model.user.info.DoctorInfo;

public interface UserDao extends AbstractDao<User> {
    User findByEmail(String email) throws DaoException;
    User findByFullName(String firstName, String lastName) throws DaoException;
    User findByEmailPassword(String email, String password) throws DaoException;
    String findUserRole(int id) throws DaoException;
    boolean isUserExist(String login) throws DaoException;
    DoctorInfo findDoctorInfoById (int id) throws DaoException;
}
