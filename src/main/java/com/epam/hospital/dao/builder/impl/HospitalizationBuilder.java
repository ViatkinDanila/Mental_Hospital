package com.epam.hospital.dao.builder.impl;


import com.epam.hospital.dao.builder.EntityBuilder;
import com.epam.hospital.dao.table_names.Column;
import com.epam.hospital.model.treatment.Hospitalization;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HospitalizationBuilder implements EntityBuilder<Hospitalization> {
    public Hospitalization build(ResultSet resultSet) throws SQLException {
        Hospitalization hospitalization = new Hospitalization();
        hospitalization.setHospitalizationId(resultSet.getInt(Column.HOSPITALIZATION_ID));
        hospitalization.setPatientId(resultSet.getInt(Column.HOSPITALIZATION_PATIENT_ID));
        hospitalization.setStatus(resultSet.getBoolean(Column.HOSPITALIZATION_STATUS));
        return hospitalization;
    }
}
