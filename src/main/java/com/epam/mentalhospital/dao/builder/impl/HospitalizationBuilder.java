package com.epam.mentalhospital.dao.builder.impl;


import com.epam.mentalhospital.dao.builder.EntityBuilder;
import com.epam.mentalhospital.dao.table_names.Column;
import com.epam.mentalhospital.model.treatment.Hospitalization;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HospitalizationBuilder implements EntityBuilder<Hospitalization> {
    public Hospitalization build(ResultSet resultSet) throws SQLException {
        Hospitalization hospitalization = new Hospitalization();
        hospitalization.setHospitalizationId(resultSet.getInt(Column.HOSPITALIZATION_ID));
        hospitalization.setChamberId(resultSet.getInt(Column.HOSPITALIZATION_CHAMBER_ID));
        hospitalization.setDateIn(resultSet.getDate(Column.HOSPITALIZATION_DATE_IN));
        hospitalization.setDateOut(resultSet.getDate(Column.HOSPITALIZATION_DATE_OUT));
        hospitalization.setPatientId(resultSet.getInt(Column.HOSPITALIZATION_PATIENT_ID));
        return hospitalization;
    }
}
