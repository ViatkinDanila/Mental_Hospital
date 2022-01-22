package com.epam.mentalhospital.dao.impl;

import com.epam.mentalhospital.dao.AbstractDao;
import com.epam.mentalhospital.dao.builder.BuilderFactory;
import com.epam.mentalhospital.dao.table_names.Column;
import com.epam.mentalhospital.dao.table_names.Table;
import com.epam.mentalhospital.model.treatment.Drug;

public class DrugDaoImpl extends AbstractDaoImpl<Drug> implements AbstractDao {
    private final static String SAVE_DRUG_QUERY = String.format(
            "INSERT INTO %s ");

    public DrugDaoImpl() {
        super(BuilderFactory.getDrugBuilder(), Table.DRUGS_TABLE, Column.DRUGS_ID);
    }
}
