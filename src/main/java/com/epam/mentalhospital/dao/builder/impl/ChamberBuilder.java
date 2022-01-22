package com.epam.mentalhospital.dao.builder.impl;


import com.epam.mentalhospital.dao.builder.EntityBuilder;
import com.epam.mentalhospital.dao.table_names.Column;
import com.epam.mentalhospital.model.hospital.Chamber;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ChamberBuilder implements EntityBuilder<Chamber> {
    public Chamber build(ResultSet resultSet) throws SQLException {
        Chamber chamber = new Chamber();
        chamber.setChamberId(resultSet.getInt(Column.CHAMBER_ID));
        chamber.setChamberTypeId(resultSet.getInt(Column.CHAMBERS_TYPE_ID));
        chamber.setHospitalId(resultSet.getInt(Column.CHAMBER_HOSPITAL_ID));
        return chamber;
    }
}
