package com.epam.hospital.service.database;

import com.epam.hospital.model.treatment.Consultation;
import com.epam.hospital.service.exception.ServiceException;

public interface ConsultationService {
    Consultation getConsultationById(int id) throws ServiceException;
}
