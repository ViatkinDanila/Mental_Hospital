package com.epam.hospital.service.database;

import com.epam.hospital.model.treatment.Drug;
import com.epam.hospital.service.exception.ServiceException;

public interface DrugService {
    Drug getDrugById(int id) throws ServiceException;
}
