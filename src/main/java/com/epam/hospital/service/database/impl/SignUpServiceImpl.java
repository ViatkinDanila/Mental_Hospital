package com.epam.hospital.service.database.impl;

import com.epam.hospital.dao.PatientCardDao;
import com.epam.hospital.dao.UserDao;
import com.epam.hospital.dao.exception.DaoException;
import com.epam.hospital.dao.helper.DaoTransactionProvider;
import com.epam.hospital.dao.impl.PatientCardDaoImpl;
import com.epam.hospital.dao.impl.UserDaoImpl;
import com.epam.hospital.model.treatment.PatientCard;
import com.epam.hospital.model.user.User;
import com.epam.hospital.service.database.SignUpService;
import com.epam.hospital.service.exception.ServiceException;

public class SignUpServiceImpl implements SignUpService {
    private static final SignUpService instance = new SignUpServiceImpl();
    
    public static SignUpService getInstance() {
        return instance;
    }

    @Override
    public void signUp(User user, PatientCard patientCard) throws ServiceException {
        UserDao userDao = new UserDaoImpl();
        PatientCardDao patientCardDao = new PatientCardDaoImpl();
        try(DaoTransactionProvider transaction = new DaoTransactionProvider()){
            transaction.initTransaction(false,userDao,patientCardDao);
            userDao.save(user);
            user = userDao.findByEmail(user.getEmail());
            patientCard.setUserId(user.getUserId());
            patientCardDao.save(patientCard);
        } catch (DaoException e){
            throw new ServiceException("Can't get user by login.", e);
        }
    }
}
