package com.epam.hospital.service.database.impl;

import com.epam.hospital.service.database.ChamberService;
import com.epam.hospital.service.exception.ServiceException;

public class ChamberServiceImpl implements ChamberService {
    private static final ChamberService instance = new ChamberServiceImpl();

    public static ChamberService getInstance(){
        return instance;
    }

    @Override
    public boolean isChamberAvailable(int chamberTypeId) throws ServiceException {

        return false;
    }
}
