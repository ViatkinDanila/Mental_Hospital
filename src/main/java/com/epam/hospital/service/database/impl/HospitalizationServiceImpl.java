package com.epam.hospital.service.database.impl;

import com.epam.hospital.service.database.HospitalizationService;
import com.epam.hospital.service.exception.ServiceException;

public class HospitalizationServiceImpl implements HospitalizationService {
    private static final HospitalizationService instance = new HospitalizationServiceImpl();

    public static HospitalizationService getInstance(){
        return instance;
    }
    @Override
    public void saveHospitalization() throws ServiceException {

    }
}
