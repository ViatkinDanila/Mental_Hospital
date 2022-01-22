package com.epam.mentalhospital.dao.impl;

import com.epam.mentalhospital.dao.HospitalizationDao;
import com.epam.mentalhospital.dao.builder.BuilderFactory;
import com.epam.mentalhospital.dao.exception.DaoException;
import com.epam.mentalhospital.dao.table_names.Column;
import com.epam.mentalhospital.dao.table_names.Table;
import com.epam.mentalhospital.model.treatment.Hospitalization;

import java.util.List;

public class HospitalizationDaoImpl extends AbstractDaoImpl<Hospitalization> implements HospitalizationDao {
    private final static String SAVE_HOSPITALIZATION_QUERY = String.format(
            "INSERT INTO %s (%s) VALUES (?)",
            Table.HOSPITALIZATION_TABLE,
            Column.HOSPITALIZATION_PATIENT_ID
    );
    private final static String SAVE_HOSPITALIZATION_CHAMBER_QUERY = String.format(
            "INSERT INTO %s (%s, %s, %s, %s) VALUES (?, ?, ?, ?)",
            Table.HOSPITALIZATION_HAS_CHAMBERS_TABLE,
            Column.HOSPITALIZATION_ID,
            Column.HOSPITALIZATION_CHAMBER_ID,
            Column.HOSPITALIZATION_DATE_IN,
            Column.HOSPITALIZATION_DATE_OUT
    );
    private final static String UPDATE_HOSPITALIZATION_QUERY = String.format(
            "UPDATE %s SET %s=? WHERE %s=?",
            Table.HOSPITALIZATION_TABLE,
            Column.HOSPITALIZATION_PATIENT_ID,
            Column.HOSPITALIZATION_ID
    );
    private final static String UPDATE_HOSPITALIZATION_CHAMBER_QUERY = String.format(
            "UPDATE %s SET %s=?, %s=?, %s=? WHERE %s=?",
            Table.HOSPITALIZATION_HAS_CHAMBERS_TABLE,
            Column.HOSPITALIZATION_CHAMBER_ID,
            Column.HOSPITALIZATION_DATE_IN,
            Column.HOSPITALIZATION_DATE_OUT,
            Column.HOSPITALIZATION_ID
    );
    private final static String FIND_BY_PATIENT_ID_QUERY = String.format(
            "SELECT * FROM %s WHERE %s=?",
            Table.HOSPITALIZATION_TABLE,
            Column.HOSPITALIZATION_PATIENT_ID
    );

    public HospitalizationDaoImpl() {
        super(BuilderFactory.getHospitalizationBuilder(), Table.HOSPITALIZATION_TABLE, Column.HOSPITALIZATION_ID);
    }

    @Override
    public void save(Hospitalization entity) throws DaoException {
//        return queryExecutor.executeUpdate(SAVE_HOSPITALIZATION_QUERY,
//                entity.getPatientId()) +
//                queryExecutor.executeUpdate(SAVE_HOSPITALIZATION_CHAMBER_QUERY,
//                        entity.getHospitalizationId(),
//                        entity.getChamberId(),
//                        entity.getDateIn(),
//                        entity.getDateOut());
    }

    @Override
    public void update(Hospitalization entity) throws DaoException {
//        return queryExecutor.executeUpdate(UPDATE_HOSPITALIZATION_QUERY,
//                entity.getPatientId(),
//                entity.getHospitalizationId()) +
//                queryExecutor.executeUpdate(UPDATE_HOSPITALIZATION_CHAMBER_QUERY,
//                        entity.getChamberId(),
//                        entity.getDateIn(),
//                        entity.getDateOut(),
//                        entity.getHospitalizationId());
    }

    @Override
    public List<Hospitalization> findByPatientId(int patientId) throws DaoException {
        return null;
    }
}
