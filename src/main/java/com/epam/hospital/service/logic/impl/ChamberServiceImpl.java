package com.epam.hospital.service.logic.impl;

import com.epam.hospital.dao.ChamberDao;
import com.epam.hospital.dao.ChamberStayingDao;
import com.epam.hospital.dao.ChamberTypeDao;
import com.epam.hospital.dao.exception.DaoException;
import com.epam.hospital.dao.helper.DaoTransactionProvider;
import com.epam.hospital.dao.impl.ChamberDaoImpl;
import com.epam.hospital.dao.impl.ChamberStayingDaoImpl;
import com.epam.hospital.dao.impl.ChamberTypeDaoImpl;
import com.epam.hospital.model.hospital.Chamber;
import com.epam.hospital.model.hospital.type.ChamberType;
import com.epam.hospital.model.treatment.ChamberStaying;
import com.epam.hospital.service.logic.ChamberService;
import com.epam.hospital.service.exception.ServiceException;
import com.epam.hospital.service.validator.Validator;
import com.epam.hospital.service.validator.impl.ChamberStayingValidatorImpl;
import com.epam.hospital.service.validator.impl.ChamberValidatorImpl;

import java.util.List;


public class ChamberServiceImpl implements ChamberService {
    private static final ChamberService instance = new ChamberServiceImpl();
    private static final Validator<Chamber> chamberValidator = new ChamberValidatorImpl();
    private static final Validator<ChamberStaying> chamberStayingValidator = new ChamberStayingValidatorImpl();

    public static ChamberService getInstance(){
        return instance;
    }

    @Override
    public boolean isChamberTypeAvailable(int chamberTypeId) throws ServiceException {
        ChamberTypeDao chamberTypeDao = new ChamberTypeDaoImpl();
        try(DaoTransactionProvider transaction = new DaoTransactionProvider()){
            transaction.initTransaction(true, chamberTypeDao);
            return chamberTypeDao.isChamberTypeAvailable(chamberTypeId);
        } catch(DaoException e){
            throw new ServiceException("Can't ckeck is chamber available.", e);
        }
    }

    @Override
    public ChamberType getChamberTypeById(int chamberTypeId) throws ServiceException {
        ChamberTypeDao chamberTypeDao = new ChamberTypeDaoImpl();
        try(DaoTransactionProvider transaction = new DaoTransactionProvider()){
            transaction.initTransaction(true, chamberTypeDao);
            return chamberTypeDao.findById(chamberTypeId);
        } catch(DaoException e){
            throw new ServiceException("Can't check is chamber available.", e);
        }
    }

    @Override
    public Chamber getAvailableChamber(int chamberTypeId) throws ServiceException {
        ChamberDao chamberDao = new ChamberDaoImpl();
        try(DaoTransactionProvider transaction = new DaoTransactionProvider()){
            transaction.initTransaction(true, chamberDao);
            return chamberDao.findChamberWithFreeBeds(chamberTypeId);
        } catch(DaoException e){
            throw new ServiceException("Can't ckeck is chamber available.", e);
        }
    }

    @Override
    public Chamber getChamberById(int chamberId) throws ServiceException {
        ChamberDao chamberDao = new ChamberDaoImpl();
        try(DaoTransactionProvider transaction = new DaoTransactionProvider()){
            transaction.initTransaction(true, chamberDao);
            return chamberDao.findById(chamberId);
        } catch(DaoException e){
            throw new ServiceException("Can't ckeck is chamber available.", e);
        }
    }

    @Override
    public void updateChamber(Chamber chamber) throws ServiceException {
        if (!chamberValidator.isValid(chamber)){
            throw new ServiceException("Invalid chamber data.");
        }
        ChamberDao chamberDao = new ChamberDaoImpl();
        try(DaoTransactionProvider transaction = new DaoTransactionProvider()){
            transaction.initTransaction(true, chamberDao);
            chamberDao.update(chamber);
        } catch(DaoException e){
            throw new ServiceException("Can't update chamber.", e);
        }
    }

    @Override
    public void updateChamberStaying(ChamberStaying chamberStaying) throws ServiceException {
        if (!chamberStayingValidator.isValid(chamberStaying)){
            throw new ServiceException("Invalid chamber staying data.");
        }
        ChamberStayingDao chamberStayingDao = new ChamberStayingDaoImpl();
        try(DaoTransactionProvider transaction = new DaoTransactionProvider()){
            transaction.initTransaction(true, chamberStayingDao);
            chamberStayingDao.update(chamberStaying);
        } catch(DaoException e){
            throw new ServiceException("Can't update chamber staying.", e);
        }
    }

    @Override
    public void updateChamberType(ChamberType chamberType) throws ServiceException {
        ChamberTypeDao chamberTypeDao = new ChamberTypeDaoImpl();
        try(DaoTransactionProvider transaction = new DaoTransactionProvider()){
            transaction.initTransaction(true, chamberTypeDao);
            chamberTypeDao.update(chamberType);
        } catch(DaoException e){
            throw new ServiceException("Can't update chamber type.", e);
        }
    }

    @Override
    public ChamberStaying getChamberStayingById(int hospitalizationId) throws ServiceException {
        ChamberStayingDao chamberStayingDao = new ChamberStayingDaoImpl();
        try(DaoTransactionProvider transaction = new DaoTransactionProvider()){
            transaction.initTransaction(true, chamberStayingDao);
            return chamberStayingDao.findById(hospitalizationId);
        } catch(DaoException e){
            throw new ServiceException("Can't get chamber staying.", e);
        }
    }

    @Override
    public List<ChamberType> getAllChamberTypes() throws ServiceException {
        ChamberTypeDao chamberTypeDao = new ChamberTypeDaoImpl();
        try(DaoTransactionProvider transaction = new DaoTransactionProvider()){
            transaction.initTransaction(chamberTypeDao);
            return chamberTypeDao.findAll();
        } catch(DaoException e){
            throw new ServiceException("Can't update chamber type.", e);
        }
    }

}
