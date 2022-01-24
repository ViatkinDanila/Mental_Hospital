package com.epam.hospital.service.database.impl;

import com.epam.hospital.dao.PatientCardDao;
import com.epam.hospital.dao.exception.DaoException;
import com.epam.hospital.dao.helper.DaoTransactionProvider;
import com.epam.hospital.dao.impl.PatientCardDaoImpl;
import com.epam.hospital.model.treatment.PatientCard;
import com.epam.hospital.service.database.PatientCardService;
import com.epam.hospital.service.exception.ServiceException;

public class PatientCardServiceImpl implements PatientCardService {
    private static final PatientCardService instance = new PatientCardServiceImpl();

    public static PatientCardService getInstance(){
        return instance;
    }

    @Override
    public PatientCard getPatientCardById(int id) throws ServiceException {
        PatientCardDao patientCardDao = new PatientCardDaoImpl();
        try(DaoTransactionProvider transaction = new DaoTransactionProvider()){
            transaction.initTransaction(patientCardDao);
            return patientCardDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException("Can't get treatment course.", e);
        }
    }
}
