package com.epam.mentalhospital.dao;

import com.epam.mentalhospital.dao.exception.DaoException;
import com.epam.mentalhospital.model.treatment.Consultation;

import java.util.List;

public interface ConsultationDao extends AbstractDao<Consultation> {
    List<Consultation> findByDoctorId(int doctorId) throws DaoException;

    List<Consultation> findByPatientId(int patientId) throws DaoException;

    List<Consultation> findByDate(String date) throws DaoException;
}
