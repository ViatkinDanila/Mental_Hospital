package com.epam.hospital.dao.impl;

import com.epam.hospital.dao.ChamberTypeDao;
import com.epam.hospital.dao.builder.BuilderFactory;
import com.epam.hospital.dao.exception.DaoException;
import com.epam.hospital.dao.table_names.Column;
import com.epam.hospital.dao.table_names.Table;
import com.epam.hospital.model.hospital.type.ChamberType;
import com.epam.hospital.model.treatment.Drug;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ChamberTypeDaoImpl extends AbstractDaoImpl<ChamberType> {
    private final String SAVE_CHAMBER_QUERY = String.format(
            "INSERT INTO %s (%s, %s, %s, %s) VALUES (?, ?, ?, ?)",
            Table.CHAMBERS_TYPE_TABLE,
            Column.CHAMBERS_TYPE_TITLE,
            Column.CHAMBERS_TYPE_NUMBER_OF_BEDS,
            Column.CHAMBERS_TYPE_PRICE,
            Column.CHAMBER_TYPE_NUMBER_OF_AVAILABLE_ROOMS
    );

    private final String UPDATE_CHAMBER_QUERY = String.format(
            "UPDATE %s SET %s=?, %s=?, %s=?, %s=? WHERE %s=?",
            Table.CHAMBERS_TYPE_TABLE,
            Column.CHAMBERS_TYPE_TITLE,
            Column.CHAMBERS_TYPE_NUMBER_OF_BEDS,
            Column.CHAMBERS_TYPE_PRICE,
            Column.CHAMBER_TYPE_NUMBER_OF_AVAILABLE_ROOMS,
            Column.CHAMBERS_TYPE_ID
    );

    public ChamberTypeDaoImpl() {
        super(BuilderFactory.getChamberTypeBuilder(), Table.CHAMBERS_TYPE_TABLE, Column.CHAMBERS_TYPE_ID);
    }

    @Override
    public void save(ChamberType entity) throws DaoException {
        try (PreparedStatement statement = pooledConnection.prepareStatement(SAVE_CHAMBER_QUERY);) {
            setParams(statement, entity, SAVE_CHAMBER_QUERY);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Can't save chamber type.", e);
        }
    }

    @Override
    public void update(ChamberType entity) throws DaoException {
        try (PreparedStatement statement = pooledConnection.prepareStatement(UPDATE_CHAMBER_QUERY);) {
            setParams(statement, entity, UPDATE_CHAMBER_QUERY);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Can't save chamber type.", e);
        }
    }

    private void setParams(PreparedStatement statement, ChamberType chamberType, String action) throws SQLException {
        statement.setString(1, chamberType.getName());
        statement.setInt(2, chamberType.getNumberOfBeds());
        statement.setDouble(3, chamberType.getPrice());
        statement.setInt(4, chamberType.getNumberOfFreeRooms());
        if (action.equals(UPDATE_CHAMBER_QUERY)) {
            statement.setInt(5, chamberType.getChamberTypeId());
        }
    }

}
