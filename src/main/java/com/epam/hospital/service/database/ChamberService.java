package com.epam.hospital.service.database;

import com.epam.hospital.service.exception.ServiceException;

public interface ChamberService {
    boolean isChamberAvailable(int chamberTypeId) throws ServiceException;
}
