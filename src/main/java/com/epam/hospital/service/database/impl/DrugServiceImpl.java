package com.epam.hospital.service.database.impl;

import com.epam.hospital.dao.AbstractDao;
import com.epam.hospital.dao.exception.DaoException;
import com.epam.hospital.dao.helper.DaoTransactionProvider;
import com.epam.hospital.dao.impl.DrugDaoImpl;
import com.epam.hospital.model.treatment.Drug;
import com.epam.hospital.service.database.DiseaseService;
import com.epam.hospital.service.database.DrugService;
import com.epam.hospital.service.exception.ServiceException;

public class DrugServiceImpl implements DrugService {
    private static final DrugService instance = new DrugServiceImpl();

    public static DrugService getInstance(){
        return instance;
    }

    @Override
    public Drug getDrugById(int id) throws ServiceException {
        AbstractDao<Drug> drugDao = new DrugDaoImpl();
        try(DaoTransactionProvider transaction = new DaoTransactionProvider()){
            transaction.initTransaction(drugDao);
            return drugDao.findById(id);
        } catch(DaoException e) {
            throw new SecurityException("Can't get drug.", e);
        }
    }
}
