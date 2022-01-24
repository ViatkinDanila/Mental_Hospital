package com.epam.hospital.service.database.impl;

import com.epam.hospital.dao.TreatmentCourseDao;
import com.epam.hospital.dao.UserDao;
import com.epam.hospital.dao.exception.DaoException;
import com.epam.hospital.dao.helper.DaoTransactionProvider;
import com.epam.hospital.dao.impl.TreatmentCourseDaoImpl;
import com.epam.hospital.dao.impl.UserDaoImpl;
import com.epam.hospital.model.user.User;
import com.epam.hospital.service.database.UserService;
import com.epam.hospital.service.exception.ServiceException;

public class UserServiceImpl implements UserService {
    private final static UserService instance = new UserServiceImpl();

    public static UserService getInstance(){
        return instance;
    }
    @Override
    public User login(String email, String password) throws ServiceException {
        return null;
    }

    @Override
    public void register(String firstName, String lastName, String number, String email, String password) throws ServiceException {

    }

    @Override
    public User getUserById(int id) throws ServiceException {
        UserDao userDao = new UserDaoImpl();
        try(DaoTransactionProvider transaction = new DaoTransactionProvider()){
            transaction.initTransaction(userDao);
            return userDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException("Can't get treatment course.", e);
        }
    }
}
