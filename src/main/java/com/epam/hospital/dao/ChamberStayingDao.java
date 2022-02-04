package com.epam.hospital.dao;

import com.epam.hospital.dao.exception.DaoException;
import com.epam.hospital.model.Entity;
import com.epam.hospital.model.treatment.ChamberStaying;

import java.util.List;

public interface ChamberStayingDao extends AbstractDao<ChamberStaying> {
    //ChamberStaying findByForeignKeys(int chamberId, int hospitalizationId) throws DaoException;
}
