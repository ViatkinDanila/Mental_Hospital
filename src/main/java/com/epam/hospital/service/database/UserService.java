package com.epam.hospital.service.database;

import com.epam.hospital.model.user.User;
import com.epam.hospital.model.user.info.DoctorInfo;
import com.epam.hospital.service.exception.ServiceException;

public interface UserService {
    User login(String email, String password) throws ServiceException;
    void register(String firstName, String lastName, String number, String email, String password) throws ServiceException;
    User getUserById(int id) throws ServiceException;
    User getUserByLogin(String email) throws ServiceException;
    String getUserRoleById(int id) throws ServiceException;
    boolean isUserExistByLogin(String login) throws ServiceException;
    DoctorInfo getDoctorInfoById(int id) throws ServiceException;
    User getUserByFullName(String firstName, String lastName) throws ServiceException;
}
