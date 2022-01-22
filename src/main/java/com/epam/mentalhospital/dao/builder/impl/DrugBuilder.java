package com.epam.mentalhospital.dao.builder.impl;

import com.epam.mentalhospital.dao.builder.EntityBuilder;
import com.epam.mentalhospital.dao.table_names.Column;
import com.epam.mentalhospital.model.treatment.Drug;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DrugBuilder implements EntityBuilder<Drug> {
    @Override
    public Drug build(ResultSet resultSet) throws SQLException {
        Drug drug = new Drug();
        drug.setDrugId(resultSet.getInt(Column.DRUGS_ID));
        return null;
    }
}
