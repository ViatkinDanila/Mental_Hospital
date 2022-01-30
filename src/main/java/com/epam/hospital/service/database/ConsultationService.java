package com.epam.hospital.service.database;

import com.epam.hospital.model.treatment.Consultation;
import com.epam.hospital.service.exception.ServiceException;

public interface ConsultationService {
    Consultation getConsultationById(int id) throws ServiceException;
    void save(Consultation consultation) throws ServiceException;
    void update(Consultation consultation) throws ServiceException;
}
