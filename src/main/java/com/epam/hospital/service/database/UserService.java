package com.epam.hospital.service.database;

import com.epam.hospital.model.user.User;
import com.epam.hospital.service.exception.ServiceException;

public interface UserService {
    User login(String email, String password) throws ServiceException;
    void register(String firstName, String lastName, String number, String email, String password) throws ServiceException;
    User getUserById(int id) throws ServiceException;
}
