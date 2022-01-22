package com.epam.mentalhospital.service;

import com.epam.mentalhospital.model.user.User;
import com.epam.mentalhospital.service.exception.ServiceException;

public interface UserService {
    User login(String email, String password) throws ServiceException;

    void register(String firstName, String lastName, String number, String email, String password) throws ServiceException;
}
