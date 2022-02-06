package com.epam.hospital.service.database.impl;

import com.epam.hospital.dao.ChamberDao;
import com.epam.hospital.dao.ChamberStayingDao;
import com.epam.hospital.dao.HospitalizationDao;
import com.epam.hospital.dao.exception.DaoException;
import com.epam.hospital.dao.helper.DaoTransactionProvider;
import com.epam.hospital.dao.impl.ChamberDaoImpl;
import com.epam.hospital.dao.impl.ChamberStayingDaoImpl;
import com.epam.hospital.dao.impl.HospitalizationDaoImpl;
import com.epam.hospital.model.treatment.ChamberStaying;
import com.epam.hospital.model.treatment.Hospitalization;
import com.epam.hospital.service.database.HospitalizationService;
import com.epam.hospital.service.exception.ServiceException;

import java.util.List;

public class HospitalizationServiceImpl implements HospitalizationService {
    private static final HospitalizationService instance = new HospitalizationServiceImpl();

    public static HospitalizationService getInstance(){
        return instance;
    }

    @Override
    public Hospitalization getHospitalizationById(int hospitalizationId) throws ServiceException {
        HospitalizationDao hospitalizationDao = new HospitalizationDaoImpl();
        try(DaoTransactionProvider transaction = new DaoTransactionProvider()){
            transaction.initTransaction(true, hospitalizationDao);
            return hospitalizationDao.findById(hospitalizationId);
        } catch(DaoException e){
            throw new SecurityException("Can't get hospitalization.", e);
        }
    }

    @Override
    public List<Hospitalization> getAllHospitalizationsByPatientCardId(int patientCardId) throws ServiceException {
        HospitalizationDao hospitalizationDao = new HospitalizationDaoImpl();
        try(DaoTransactionProvider transaction = new DaoTransactionProvider()){
            transaction.initTransaction(hospitalizationDao);
            return hospitalizationDao.findByPatientId(patientCardId);
        } catch(DaoException e){
            throw new SecurityException("Can't get hospitalizations by patient card id.", e);
        }
    }

    @Override
    public List<Hospitalization> getAllHospitalizationsByDoctorCardId(int doctorId) throws ServiceException {
        HospitalizationDao hospitalizationDao = new HospitalizationDaoImpl();
        try(DaoTransactionProvider transaction = new DaoTransactionProvider()){
            transaction.initTransaction(hospitalizationDao);
            return hospitalizationDao.findByDoctorId(doctorId);
        } catch(DaoException e){
            throw new SecurityException("Can't get hospitalizations by patient card id.", e);
        }
    }

    @Override
    public void saveHospitalization(Hospitalization hospitalization, ChamberStaying chamberStaying) throws ServiceException {
        HospitalizationDao hospitalizationDao = new HospitalizationDaoImpl();
        ChamberStayingDao chamberStayingDao = new ChamberStayingDaoImpl();
        try(DaoTransactionProvider transaction = new DaoTransactionProvider()){
            transaction.initTransaction(false, hospitalizationDao, chamberStayingDao);

            hospitalizationDao.save(hospitalization);
            int hospitalizationId = hospitalizationDao.getHospitalizationIdByPatientId(hospitalization.getPatientId());

            chamberStaying.setHospitalizationId(hospitalizationId);
            chamberStayingDao.save(chamberStaying);
            transaction.commit();
        } catch(DaoException e){
            throw new SecurityException("Can't save hospitalization, chamber staying.", e);
        }
    }

    @Override
    public void update(Hospitalization hospitalization) throws ServiceException {
        HospitalizationDao hospitalizationDao = new HospitalizationDaoImpl();
        try(DaoTransactionProvider transaction = new DaoTransactionProvider()){
            transaction.initTransaction(true, hospitalizationDao);
            hospitalizationDao.update(hospitalization);
        } catch(DaoException e){
            throw new SecurityException("Can't update hospitalization.", e);
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
}
