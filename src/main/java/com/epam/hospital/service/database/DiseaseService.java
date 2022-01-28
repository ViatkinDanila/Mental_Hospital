package com.epam.hospital.service.database;

import com.epam.hospital.model.treatment.Disease;
import com.epam.hospital.service.exception.ServiceException;

public interface DiseaseService {
    Disease getDiseaseById(int id) throws ServiceException;
    int getDiseaseIdByName(String name) throws ServiceException;
}
