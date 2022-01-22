package com.epam.mentalhospital.dao.impl;

import com.epam.mentalhospital.dao.ConsultationDao;
import com.epam.mentalhospital.dao.builder.BuilderFactory;
import com.epam.mentalhospital.dao.exception.DaoException;
import com.epam.mentalhospital.dao.table_names.Column;
import com.epam.mentalhospital.dao.table_names.Table;
import com.epam.mentalhospital.model.treatment.Consultation;

import java.util.List;

public class ConsultationDaoImpl extends AbstractDaoImpl<Consultation> implements ConsultationDao {
    private final static String SAVE_CONSULTATION_QUERY = String.format(
            "INSERT INTO %s (%s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?)",
            Table.CONSULTATION_TABLE,
            Column.CONSULTATION_TYPE_ID,
            Column.CONSULTATION_DOCTOR_ID,
            Column.CONSULTATION_PATIENT_ID,
            Column.CONSULTATION_DATE,
            Column.CONSULTATION_DURATION
    );
    private final static String UPDATE_CONSULTATION_QUERY = String.format(
            "UPDATE %s SET %s=?, %s=?, %s=?, %s=?, %s=? WHERE %s=?",
            Table.CONSULTATION_TABLE,
            Column.CONSULTATION_TYPE_ID,
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
    private final static String FIND_BY_DATE_QUERY = String.format(
            "SELECT * FROM %s WHERE %s=?",
            Table.CONSULTATION_TABLE,
            Column.CONSULTATION_DATE
    );

    public ConsultationDaoImpl() {
        super(BuilderFactory.getConsultationBuilder(), Table.CONSULTATION_TABLE, Column.CONSULTATION_ID);
    }

    @Override
    public void save(Consultation entity) throws DaoException {
//        return queryExecutor.executeUpdate(SAVE_CONSULTATION_QUERY,
//                entity.getCommunicationTypeId(),
//                entity.getDoctorId(),
//                entity.getPatientId(),
//                entity.getDate(),
//                entity.getDuration());
    }

    @Override
    public void update(Consultation entity) throws DaoException {
//        return queryExecutor.executeUpdate(SAVE_CONSULTATION_QUERY,
//                entity.getCommunicationTypeId(),
//                entity.getDoctorId(),
//                entity.getPatientId(),
//                entity.getDate(),
//                entity.getDuration(),
//                entity.getConsultationId());
    }

    @Override
    public List<Consultation> findByDoctorId(int doctorId) throws DaoException {
        return null;
    }

    @Override
    public List<Consultation> findByPatientId(int patientId) throws DaoException {
        return null;
    }

    @Override
    public List<Consultation> findByDate(String date) throws DaoException {
        return null;
    }
}
