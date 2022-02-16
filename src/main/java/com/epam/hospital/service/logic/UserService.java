package com.epam.hospital.service.logic;

import com.epam.hospital.model.user.User;
import com.epam.hospital.model.user.info.DoctorInfo;
import com.epam.hospital.service.exception.ServiceException;

import java.util.List;

public interface UserService {
    User login(String email, String password) throws ServiceException;
    User getUserById(int id) throws ServiceException;
    User getUserByLogin(String email) throws ServiceException;
    String getUserRoleById(int id) throws ServiceException;
    boolean isUserExistByLogin(String login) throws ServiceException;
    DoctorInfo getDoctorInfoById(int id) throws ServiceException;
    User getUserByFullName(String firstName, String lastName) throws ServiceException;
    List<User> getAllDoctors(int doctorRoleId) throws ServiceException;
    List<User> getAll() throws ServiceException;
    Integer getUserRoleId() throws ServiceException;
    void saveUser(User user) throws ServiceException;
}
