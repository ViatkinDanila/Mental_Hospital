package com.epam.hospital.service.database.impl;

import com.epam.hospital.dao.AbstractDao;
import com.epam.hospital.dao.ConsultationDao;
import com.epam.hospital.dao.DiseaseDao;
import com.epam.hospital.dao.exception.DaoException;
import com.epam.hospital.dao.helper.DaoTransactionProvider;
import com.epam.hospital.dao.impl.ConsultationDaoImpl;
import com.epam.hospital.dao.impl.DiseaseDaoImpl;
import com.epam.hospital.model.treatment.Disease;
import com.epam.hospital.service.database.ConsultationService;
import com.epam.hospital.service.database.DiseaseService;
import com.epam.hospital.service.exception.ServiceException;

import java.util.List;

public class DiseaseServiceImpl implements DiseaseService {
    private static final DiseaseService instance = new DiseaseServiceImpl();

    public static DiseaseService getInstance(){
        return instance;
    }

    @Override
    public Disease getDiseaseById(int id) throws ServiceException {
        AbstractDao<Disease> consultationDao = new DiseaseDaoImpl();
        try(DaoTransactionProvider transaction = new DaoTransactionProvider()){
            transaction.initTransaction(consultationDao);
            return consultationDao.findById(id);
        } catch(DaoException e){
            throw new SecurityException("Can't get consultation.", e);
        }
    }
}
