package com.epam.hospital.service.database;

import com.epam.hospital.model.treatment.PatientCard;
import com.epam.hospital.service.exception.ServiceException;

public interface PatientCardService {
    PatientCard getPatientCardById(int id) throws ServiceException;
}
