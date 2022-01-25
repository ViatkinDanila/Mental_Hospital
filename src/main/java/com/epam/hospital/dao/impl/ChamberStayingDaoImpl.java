package com.epam.hospital.dao.impl;

import com.epam.hospital.dao.AbstractDao;
import com.epam.hospital.dao.builder.BuilderFactory;
import com.epam.hospital.dao.exception.DaoException;
import com.epam.hospital.dao.table_names.Column;
import com.epam.hospital.dao.table_names.Table;
import com.epam.hospital.model.treatment.ChamberStaying;
import com.epam.hospital.model.treatment.DiseaseSymptom;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ChamberStayingDaoImpl extends AbstractDaoImpl<ChamberStaying> {
    private final static String SAVE_CHAMBER_STAYING_QUERY = String.format(
            "INSERT INTO %s (%s, %s, %s, %s) VALUES (?, ?, ?, ?)",
            Table.CHAMBER_STAYING_TABLE,
            Column.CHAMBER_STAYING_DATE_IN,
            Column.CHAMBER_STAYING_DATE_OUT,
            Column.CHAMBER_STAYING_CHAMBER_ID,
            Column.CHAMBER_STAYING_HOSPITALIZATION_ID
    );
    private final static String UPDATE_CHAMBER_STAYING_QUERY = String.format(
            "UPDATE %s SET %s=?, %s=? WHERE %s=? AND %s=?",
            Table.CHAMBER_STAYING_TABLE,
            Column.CHAMBER_STAYING_DATE_IN,
            Column.CHAMBER_STAYING_DATE_OUT,
            Column.CHAMBER_STAYING_CHAMBER_ID,
            Column.CHAMBER_STAYING_HOSPITALIZATION_ID
    );
    
    public ChamberStayingDaoImpl(){
        super(BuilderFactory.getChamberStaying(), Table.CHAMBER_STAYING_TABLE, Column.CHAMBER_STAYING_HOSPITALIZATION_ID);
    }

    @Override
    public void save(ChamberStaying entity) throws DaoException {
        try (PreparedStatement statement = pooledConnection.prepareStatement(SAVE_CHAMBER_STAYING_QUERY);) {
            setParams(statement, entity, SAVE_CHAMBER_STAYING_QUERY);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Can't save chamber staying.", e);
        }
    }

    @Override
    public void update(ChamberStaying entity) throws DaoException {
        try (PreparedStatement statement = pooledConnection.prepareStatement(UPDATE_CHAMBER_STAYING_QUERY);) {
            setParams(statement, entity, UPDATE_CHAMBER_STAYING_QUERY);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Can't update chamber staying.", e);
        }
    }
    private void setParams(PreparedStatement statement, ChamberStaying chamberStaying, String action) throws SQLException {
        statement.setDate(1, chamberStaying.getDateIn());
        statement.setDate(2, chamberStaying.getDateOut());
        statement.setInt(3, chamberStaying.getChamber_id());
        statement.setInt(4, chamberStaying.getHospitalization_id());
    }
}
