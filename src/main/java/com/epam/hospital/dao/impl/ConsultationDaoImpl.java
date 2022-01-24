package com.epam.hospital.dao.impl;

import com.epam.hospital.dao.ConsultationDao;
import com.epam.hospital.dao.builder.BuilderFactory;
import com.epam.hospital.dao.exception.DaoException;
import com.epam.hospital.dao.table_names.Column;
import com.epam.hospital.dao.table_names.Table;
import com.epam.hospital.model.hospital.type.ChamberType;
import com.epam.hospital.model.treatment.Consultation;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsultationDaoImpl extends AbstractDaoImpl<Consultation> implements ConsultationDao {
    private final static String SAVE_CONSULTATION_QUERY = String.format(
            "INSERT INTO %s (%s, %s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?, ?)",
            Table.CONSULTATION_TABLE,
            Column.CONSULTATION_COMMUNICATION_TYPE,
            Column.CONSULTATION_TREATMENT_COURSE_ID,
            Column.CONSULTATION_DOCTOR_ID,
            Column.CONSULTATION_PATIENT_ID,
            Column.CONSULTATION_DATE,
            Column.CONSULTATION_DURATION
    );
    private final static String UPDATE_CONSULTATION_QUERY = String.format(
            "UPDATE %s SET %s=?, %s=?, %s=?, %s=?, %s=?, %s=? WHERE %s=?",
            Table.CONSULTATION_TABLE,
            Column.CONSULTATION_COMMUNICATION_TYPE,
            Column.CONSULTATION_TREATMENT_COURSE_ID,
            Column.CONSULTATION_DOCTOR_ID,
            Column.CONSULTATION_PATIENT_ID,
            Column.CONSULTATION_DATE,
            Column.CONSULTATION_DURATION,
            Column.CONSULTATION_ID
    );
    private final static String FIND_BY_DOCTOR_ID_QUERY = String.format(
            "SELECT * FROM %s WHERE %s=?",
            Table.CONSULTATION_TABLE,
            Column.CONSULTATION_DOCTOR_ID
    );
    private final static String FIND_BY_PATIENT_ID_QUERY = String.format(
            "SELECT * FROM %s WHERE %s=?",
            Table.CONSULTATION_TABLE,
            Column.CONSULTATION_PATIENT_ID
    );


    public ConsultationDaoImpl() {
        super(BuilderFactory.getConsultationBuilder(), Table.CONSULTATION_TABLE, Column.CONSULTATION_ID);
    }

    @Override
    public void save(Consultation entity) throws DaoException {
        try (PreparedStatement statement = pooledConnection.prepareStatement(SAVE_CONSULTATION_QUERY);) {
            setParams(statement, entity, SAVE_CONSULTATION_QUERY);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Can't save chamber type.", e);
        }
    }

    @Override
    public void update(Consultation entity) throws DaoException {
        try (PreparedStatement statement = pooledConnection.prepareStatement(UPDATE_CONSULTATION_QUERY);) {
            setParams(statement, entity, UPDATE_CONSULTATION_QUERY);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Can't save chamber type.", e);
        }
    }

    @Override
    public List<Consultation> findByDoctorId(int doctorId) throws DaoException {
        return findByField(Column.CONSULTATION_DOCTOR_ID, doctorId);
    }

    @Override
    public List<Consultation> findByPatientId(int patientId) throws DaoException {
        return findByField(Column.CONSULTATION_PATIENT_ID, patientId);
    }


    private final void setParams(PreparedStatement statement, Consultation consultation, String action) throws SQLException {
        statement.setString(1, String.valueOf(consultation.getCommunicationType()));
        statement.setInt(2, consultation.getTreatmentCourseId());
        statement.setInt(3, consultation.getDoctorId());
        statement.setInt(4, consultation.getPatientId());
        statement.setDate(5, Date.valueOf(consultation.getDate()));
        statement.setInt(6, consultation.getDuration());
        if (action.equals(UPDATE_CONSULTATION_QUERY)) {
            statement.setInt(6, consultation.getDuration());

        }
    }
}
