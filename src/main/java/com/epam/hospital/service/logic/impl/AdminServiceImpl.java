package com.epam.hospital.service.logic.impl;

import com.epam.hospital.dao.UserDao;
import com.epam.hospital.dao.exception.DaoException;
import com.epam.hospital.dao.helper.DaoTransactionProvider;
import com.epam.hospital.dao.impl.UserDaoImpl;
import com.epam.hospital.model.user.User;
import com.epam.hospital.model.user.info.DoctorInfo;
import com.epam.hospital.service.logic.AdminService;
import com.epam.hospital.service.exception.ServiceException;
import com.epam.hospital.service.validator.Validator;
import com.epam.hospital.service.validator.impl.UserValidatorImpl;

public class AdminServiceImpl implements AdminService {
    private static final AdminService instance = new AdminServiceImpl();
    private static final Validator<User> userValidator = new UserValidatorImpl();

    public static AdminService getInstance(){
        return instance;
    }

    @Override
    public boolean isUserBanned(int userId) throws SecurityException {

        return false;
    }

    @Override
    public void setUserBanStatusById(int userId, boolean banStatus) throws ServiceException {
        UserDao userDao = new UserDaoImpl();
        try(DaoTransactionProvider transaction = new DaoTransactionProvider()){
            transaction.initTransaction(true, userDao);
            User user = userDao.findById(userId);
            user.setIsBanned(banStatus);
            userDao.update(user);
        } catch(DaoException e){
            throw new ServiceException("Can't ban user.", e);
        }
    }

    @Override
    public void saveUser(User user, DoctorInfo doctorInfo) throws ServiceException {
        UserDao userDao = new UserDaoImpl();
        if (!userValidator.isValid(user)){
            throw new ServiceException("Invalid user data.");
        }
        try(DaoTransactionProvider transaction = new DaoTransactionProvider()){
            transaction.initTransaction(false, userDao);
            userDao.save(user);
            userDao.saveDoctorInfo(doctorInfo);
            transaction.commit();
        } catch (DaoException e){
            throw new ServiceException("Can't save user/doctor info.", e);
        }
    }
}
