package com.epam.hospital.dao.builder.impl;

import com.epam.hospital.dao.builder.EntityBuilder;
import com.epam.hospital.dao.table_names.Column;
import com.epam.hospital.model.treatment.ChamberStaying;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ChamberStayingBuilder implements EntityBuilder<ChamberStaying> {
    @Override
    public ChamberStaying build(ResultSet resultSet) throws SQLException {
        ChamberStaying chamberStaying = new ChamberStaying();
        chamberStaying.setChamber_id(resultSet.getInt(Column.CHAMBER_STAYING_CHAMBER_ID));
        chamberStaying.setHospitalization_id((resultSet.getInt(Column.CHAMBER_STAYING_HOSPITALIZATION_ID)));
        chamberStaying.setDateIn(resultSet.getDate(Column.CHAMBER_STAYING_DATE_IN));
        chamberStaying.setDateOut(resultSet.getDate(Column.CHAMBER_STAYING_DATE_OUT));
        return chamberStaying;
    }
}
