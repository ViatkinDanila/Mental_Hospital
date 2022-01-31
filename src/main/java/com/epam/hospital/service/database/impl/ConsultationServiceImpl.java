package com.epam.hospital.service.database.impl;

import com.epam.hospital.dao.ConsultationDao;
import com.epam.hospital.dao.exception.DaoException;
import com.epam.hospital.dao.helper.DaoTransactionProvider;
import com.epam.hospital.dao.impl.ConsultationDaoImpl;
import com.epam.hospital.model.treatment.Consultation;
import com.epam.hospital.service.database.ConsultationService;
import com.epam.hospital.service.exception.ServiceException;

import java.util.List;

public class ConsultationServiceImpl implements ConsultationService {
    private static final ConsultationService instance = new ConsultationServiceImpl();

    private ConsultationServiceImpl(){
    }

    public static ConsultationService getInstance(){
        return instance;
    }

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

    @Override
    public Consultation getConsultationByDoctorId(int id) throws SecurityException {
        ConsultationDao consultationDao = new ConsultationDaoImpl();
        try(DaoTransactionProvider transaction = new DaoTransactionProvider()){
            transaction.initTransaction(true, consultationDao);
            List<Consultation> consultations = consultationDao.findByDoctorId(id);
            return consultations.get(consultations.size() - 1);
        } catch(DaoException e){
            throw new SecurityException("Can't get consultation.", e);
        }
    }

    @Override
    public void save(Consultation consultation) throws ServiceException {
        ConsultationDao consultationDao = new ConsultationDaoImpl();
        try(DaoTransactionProvider transaction = new DaoTransactionProvider()){
            transaction.initTransaction(true, consultationDao);
            consultationDao.save(consultation);
        } catch(DaoException e){
            throw new SecurityException("Can't save consultation.", e);
        }
    }

    @Override
    public void update(Consultation consultation) throws ServiceException {
        ConsultationDao consultationDao = new ConsultationDaoImpl();
        try(DaoTransactionProvider transaction = new DaoTransactionProvider()){
            transaction.initTransaction(true, consultationDao);
            consultationDao.update(consultation);
        } catch(DaoException e){
            throw new SecurityException("Can't update consultation.", e);
        }
    }

    @Override
    public List<Consultation> getAllConsultationsByPatientCardId(int patientCardId) throws ServiceException {
        ConsultationDao consultationDao = new ConsultationDaoImpl();
        try(DaoTransactionProvider transaction = new DaoTransactionProvider()){
            transaction.initTransaction(true, consultationDao);
            return consultationDao.findByPatientId(patientCardId);
        } catch(DaoException e){
            throw new SecurityException("Can't get consultations by patient id.", e);
        }
    }

    @Override
    public List<Consultation> getAllConsultationsByDoctorId(int doctorId) throws ServiceException {
        ConsultationDao consultationDao = new ConsultationDaoImpl();
        try(DaoTransactionProvider transaction = new DaoTransactionProvider()){
            transaction.initTransaction(true, consultationDao);
            return consultationDao.findByDoctorId(doctorId);
        } catch(DaoException e){
            throw new SecurityException("Can't get consultations by doctor id.", e);
        }
    }
}
