package com.epam.hospital.service.database;

import com.epam.hospital.model.treatment.Disease;
import com.epam.hospital.service.exception.ServiceException;

import java.util.List;

public interface DiseaseService {
    Disease getDiseaseById(int id) throws ServiceException;
    List<Disease> getAll() throws ServiceException;
    int getDiseaseIdByName(String name) throws ServiceException;
    void save(Disease disease) throws ServiceException;
}
