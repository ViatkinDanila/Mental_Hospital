package com.epam.hospital.service.database.impl;

import com.epam.hospital.dao.ChamberTypeDao;
import com.epam.hospital.dao.UserDao;
import com.epam.hospital.dao.exception.DaoException;
import com.epam.hospital.dao.helper.DaoTransactionProvider;
import com.epam.hospital.dao.impl.ChamberTypeDaoImpl;
import com.epam.hospital.dao.impl.UserDaoImpl;
import com.epam.hospital.model.user.User;
import com.epam.hospital.service.database.AdminService;

public class AdminServiceImpl implements AdminService {
    private static final AdminService instance = new AdminServiceImpl();

    public static AdminService getInstance(){
        return instance;
    }

    @Override
    public boolean isUserBanned(int userId) throws SecurityException {

        return false;
    }

    @Override
    public void setUserBanStatusById(int userId, boolean banStatus) throws SecurityException {
        UserDao userDao = new UserDaoImpl();
        try(DaoTransactionProvider transaction = new DaoTransactionProvider()){
            transaction.initTransaction(true, userDao);
            User user = userDao.findById(userId);
            user.setBanned(banStatus);
            userDao.update(user);
        } catch(DaoException e){
            throw new SecurityException("Can't ban user.", e);
        }
    }

}
