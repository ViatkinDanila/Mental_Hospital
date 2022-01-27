package com.epam.hospital.dao;

import com.epam.hospital.dao.exception.DaoException;
import com.epam.hospital.model.treatment.PatientCard;

public interface PatientCardDao extends AbstractDao<PatientCard> {
    PatientCard findByPatientId(int patientId) throws DaoException;
    int findPatientCardIdByUserId(int userId) throws DaoException;
}
