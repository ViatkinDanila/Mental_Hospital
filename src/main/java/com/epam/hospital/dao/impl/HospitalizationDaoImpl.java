package com.epam.hospital.dao.impl;

import com.epam.hospital.dao.HospitalizationDao;
import com.epam.hospital.dao.builder.BuilderFactory;
import com.epam.hospital.dao.exception.DaoException;
import com.epam.hospital.dao.table_names.Column;
import com.epam.hospital.dao.table_names.Table;
import com.epam.hospital.model.treatment.Drug;
import com.epam.hospital.model.treatment.Hospitalization;

import java.sql.PreparedStatement;
import java.sql.SQLException;
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

    public HospitalizationDaoImpl() {
        super(BuilderFactory.getHospitalizationBuilder(), Table.HOSPITALIZATION_TABLE, Column.HOSPITALIZATION_ID);
    }

    @Override
    public void save(Hospitalization entity) throws DaoException {
        try (PreparedStatement statement = pooledConnection.prepareStatement(SAVE_HOSPITALIZATION_QUERY);) {
            setParams(statement, entity, SAVE_HOSPITALIZATION_QUERY);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Can't save hospitalization.", e);
        }
    }

    @Override
    public void update(Hospitalization entity) throws DaoException {
        try (PreparedStatement statement = pooledConnection.prepareStatement(UPDATE_HOSPITALIZATION_QUERY);) {
            setParams(statement, entity, UPDATE_HOSPITALIZATION_QUERY);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Can't update hospitalization.", e);
        }
    }

    @Override
    public List<Hospitalization> findByPatientId(int patientId) throws DaoException {
        return findByField(Column.HOSPITALIZATION_PATIENT_ID,patientId);
    }

    private void setParams(PreparedStatement statement, Hospitalization hospitalization, String action) throws SQLException{
        statement.setInt(1,hospitalization.getPatientId());
        if (action.equals(UPDATE_HOSPITALIZATION_QUERY)){
            statement.setInt(2, hospitalization.getHospitalizationId());
        }
    }
}
