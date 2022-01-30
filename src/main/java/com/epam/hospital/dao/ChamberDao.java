package com.epam.hospital.dao;

import com.epam.hospital.dao.exception.DaoException;
import com.epam.hospital.model.hospital.Chamber;

public interface ChamberDao extends AbstractDao<Chamber> {
    Chamber findChamberWithFreeBeds(int chamberTypeId) throws DaoException;
}
