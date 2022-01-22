package com.epam.mentalhospital.dao;

import com.epam.mentalhospital.dao.exception.DaoException;
import com.epam.mentalhospital.model.treatment.PatientCard;

public interface PatientCardDao extends AbstractDao<PatientCard> {
    PatientCard findByPatientId(int patientId) throws DaoException;
}
