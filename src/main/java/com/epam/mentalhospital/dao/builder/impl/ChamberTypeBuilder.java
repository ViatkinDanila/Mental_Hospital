package com.epam.mentalhospital.dao.builder.impl;

import com.epam.mentalhospital.dao.builder.EntityBuilder;
import com.epam.mentalhospital.dao.table_names.Column;
import com.epam.mentalhospital.model.hospital.type.ChamberType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ChamberTypeBuilder implements EntityBuilder<ChamberType> {
    public ChamberType build(ResultSet resultSet) throws SQLException {
        ChamberType chamberType = new ChamberType();
        chamberType.setChamberId(resultSet.getInt(Column.CHAMBERS_TYPE_ID));
        chamberType.setName(resultSet.getString(Column.CHAMBERS_TYPE_TITLE));
        chamberType.setPrice(resultSet.getDouble(Column.CHAMBERS_TYPE_PRICE));
        chamberType.setNumberOfBeds(resultSet.getInt(Column.CHAMBERS_TYPE_NUMBER_OF_BEDS));
        chamberType.setNumberOfFreeRooms(resultSet.getInt(Column.CHAMBER_NUMBER_OF_AVAILABLE_ROOMS));
        return chamberType;
    }
}
