package com.epam.hospital.service.database;

import com.epam.hospital.service.exception.ServiceException;

public interface HospitalizationService {
    void saveHospitalization() throws ServiceException;
}
