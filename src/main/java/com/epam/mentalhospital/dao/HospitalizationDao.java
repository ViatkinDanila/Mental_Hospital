package com.epam.mentalhospital.dao;

import com.epam.mentalhospital.dao.exception.DaoException;
import com.epam.mentalhospital.model.treatment.Hospitalization;

import java.util.List;

public interface HospitalizationDao extends AbstractDao<Hospitalization> {
    List<Hospitalization> findByPatientId(int patientId) throws DaoException;
}
