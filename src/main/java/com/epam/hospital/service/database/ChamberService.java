package com.epam.hospital.service.database;

import com.epam.hospital.model.hospital.Chamber;
import com.epam.hospital.model.hospital.type.ChamberType;
import com.epam.hospital.model.treatment.ChamberStaying;
import com.epam.hospital.service.exception.ServiceException;

public interface ChamberService {
    boolean isChamberTypeAvailable(int chamberTypeId) throws ServiceException;
    ChamberType getChamberTypeById(int chamberTypeId)  throws ServiceException;
    Chamber getAvailableChamber(int chamberTypeId) throws ServiceException;
    ChamberStaying getChamberStayingById(int chamberId, int hospitalizationId) throws ServiceException;
    void updateChamber(Chamber chamber)  throws ServiceException;
    void updateChamberType(ChamberType chabmerType) throws ServiceException;
    void updateChamberStaying(ChamberStaying chamberStaying) throws ServiceException;
}
