package com.epam.mentalhospital.dao.impl;

import com.epam.mentalhospital.dao.ChamberTypeDao;
import com.epam.mentalhospital.dao.builder.BuilderFactory;
import com.epam.mentalhospital.dao.exception.DaoException;
import com.epam.mentalhospital.dao.table_names.Column;
import com.epam.mentalhospital.dao.table_names.Table;
import com.epam.mentalhospital.model.hospital.type.ChamberType;

public class ChamberTypeDaoImpl extends AbstractDaoImpl<ChamberType> implements ChamberTypeDao {

    public ChamberTypeDaoImpl() {
        super(BuilderFactory.getChamberTypeBuilder(), Table.CHAMBERS_TYPE_TABLE, Column.CHAMBERS_TYPE_ID);
    }

    @Override
    public void save(ChamberType entity) throws DaoException {
//        throw new DaoException("ChamberType cannot be save. Admin do it manually.");
    }

    @Override
    public void update(ChamberType entity) throws DaoException {
//        throw new DaoException("ChamberType cannot be updated. Admin do it manually.");
    }
}
