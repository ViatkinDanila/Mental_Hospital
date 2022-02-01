package com.epam.hospital.service.database.impl;

import com.epam.hospital.dao.ChamberDao;
import com.epam.hospital.dao.ChamberStayingDao;
import com.epam.hospital.dao.ChamberTypeDao;
import com.epam.hospital.dao.ConsultationDao;
import com.epam.hospital.dao.exception.DaoException;
import com.epam.hospital.dao.helper.DaoTransactionProvider;
import com.epam.hospital.dao.impl.ChamberDaoImpl;
import com.epam.hospital.dao.impl.ChamberStayingDaoImpl;
import com.epam.hospital.dao.impl.ChamberTypeDaoImpl;
import com.epam.hospital.dao.impl.ConsultationDaoImpl;
import com.epam.hospital.model.hospital.Chamber;
import com.epam.hospital.model.hospital.type.ChamberType;
import com.epam.hospital.model.treatment.ChamberStaying;
import com.epam.hospital.service.database.ChamberService;
import com.epam.hospital.service.exception.ServiceException;

import java.util.List;


public class ChamberServiceImpl implements ChamberService {
    private static final ChamberService instance = new ChamberServiceImpl();

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
            throw new SecurityException("Can't ckeck is chamber available.", e);
        }
    }

    @Override
    public ChamberType getChamberTypeById(int chamberTypeId) throws ServiceException {
        ChamberTypeDao chamberTypeDao = new ChamberTypeDaoImpl();
        try(DaoTransactionProvider transaction = new DaoTransactionProvider()){
            transaction.initTransaction(true, chamberTypeDao);
            return chamberTypeDao.findById(chamberTypeId);
        } catch(DaoException e){
            throw new SecurityException("Can't ckeck is chamber available.", e);
        }
    }

    @Override
    public Chamber getAvailableChamber(int chamberTypeId) throws ServiceException {
        ChamberDao chamberDao = new ChamberDaoImpl();
        try(DaoTransactionProvider transaction = new DaoTransactionProvider()){
            transaction.initTransaction(true, chamberDao);
            return chamberDao.findChamberWithFreeBeds(chamberTypeId);
        } catch(DaoException e){
            throw new SecurityException("Can't ckeck is chamber available.", e);
        }
    }

    @Override
    public void updateChamber(Chamber chamber) throws ServiceException {
        ChamberDao chamberDao = new ChamberDaoImpl();
        try(DaoTransactionProvider transaction = new DaoTransactionProvider()){
            transaction.initTransaction(true, chamberDao);
            chamberDao.update(chamber);
        } catch(DaoException e){
            throw new SecurityException("Can't update chamber.", e);
        }
    }

    @Override
    public void updateChamberStaying(ChamberStaying chamberStaying) throws ServiceException {
        ChamberStayingDao chamberStayingDao = new ChamberStayingDaoImpl();
        try(DaoTransactionProvider transaction = new DaoTransactionProvider()){
            transaction.initTransaction(true, chamberStayingDao);
            chamberStayingDao.update(chamberStaying);
        } catch(DaoException e){
            throw new SecurityException("Can't update chamber staying.", e);
        }
    }

    @Override
    public void updateChamberType(ChamberType chamberType) throws ServiceException {
        ChamberTypeDao chamberTypeDao = new ChamberTypeDaoImpl();
        try(DaoTransactionProvider transaction = new DaoTransactionProvider()){
            transaction.initTransaction(true, chamberTypeDao);
            chamberTypeDao.update(chamberType);
        } catch(DaoException e){
            throw new SecurityException("Can't update chamber type.", e);
        }
    }

    @Override
    public ChamberStaying getChamberStayingById(int chamberId, int hospitalizationId) throws ServiceException {
        ChamberStayingDao chamberStayingDao = new ChamberStayingDaoImpl();
        try(DaoTransactionProvider transaction = new DaoTransactionProvider()){
            transaction.initTransaction(true, chamberStayingDao);
            return chamberStayingDao.findById(chamberId, hospitalizationId);
        } catch(DaoException e){
            throw new SecurityException("Can't get chamber staying.", e);
        }
    }

    @Override
    public List<ChamberType> getAllChamberTypes() throws ServiceException {
        ChamberTypeDao chamberTypeDao = new ChamberTypeDaoImpl();
        try(DaoTransactionProvider transaction = new DaoTransactionProvider()){
            transaction.initTransaction(chamberTypeDao);
            return chamberTypeDao.findAll();
        } catch(DaoException e){
            throw new SecurityException("Can't update chamber type.", e);
        }
    }
}
