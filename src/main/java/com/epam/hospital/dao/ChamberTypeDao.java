package com.epam.hospital.dao;

import com.epam.hospital.dao.exception.DaoException;
import com.epam.hospital.model.hospital.type.ChamberType;

public interface ChamberTypeDao extends AbstractDao<ChamberType> {
//    Chamber findByNumberOfBeds(int number) throws DaoException;
//    Chamber findByPrice(int price) throws DaoException;
    boolean isChamberTypeAvailable(int chamberTypeId) throws DaoException;
}
