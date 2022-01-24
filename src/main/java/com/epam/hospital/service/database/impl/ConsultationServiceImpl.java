package com.epam.hospital.service.database.impl;

import com.epam.hospital.dao.ConsultationDao;
import com.epam.hospital.dao.exception.DaoException;
import com.epam.hospital.dao.helper.DaoTransactionProvider;
import com.epam.hospital.dao.impl.ConsultationDaoImpl;
import com.epam.hospital.model.treatment.Consultation;
import com.epam.hospital.service.database.ConsultationService;

public class ConsultationServiceImpl implements ConsultationService {
    private static ConsultationService instance = new ConsultationServiceImpl();

    public ConsultationServiceImpl getInstance

    @Override
    public Consultation getConsultationById(int id) throws SecurityException {
        ConsultationDao consultationDao = new ConsultationDaoImpl();
        try(DaoTransactionProvider transaction = new DaoTransactionProvider()){
            transaction.initTransaction(true, consultationDao);
            return consultationDao.findById(id);
        } catch(DaoException e){
            throw new SecurityException("Can't get consultation.", e);
        }
    }
}
