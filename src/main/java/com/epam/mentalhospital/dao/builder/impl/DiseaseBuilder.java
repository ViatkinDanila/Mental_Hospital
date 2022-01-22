package com.epam.mentalhospital.dao.builder.impl;

import com.epam.mentalhospital.dao.table_names.Column;
import com.epam.mentalhospital.model.treatment.Disease;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DiseaseBuilder {
    public static Disease build(ResultSet resultSet) throws SQLException {
        Disease disease = new Disease();
        disease.setDiseaseId(resultSet.getInt(Column.DISEASES_ID));
        disease.setDescription(resultSet.getString(Column.DISEASES_DESCRIPTION));
        disease.setName(resultSet.getString(Column.DISEASES_NAME));
        return disease;
    }
}