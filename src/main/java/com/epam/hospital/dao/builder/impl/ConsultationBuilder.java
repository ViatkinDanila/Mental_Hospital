package com.epam.hospital.dao.builder.impl;

import com.epam.hospital.dao.builder.EntityBuilder;
import com.epam.hospital.dao.table_names.Column;
import com.epam.hospital.model.treatment.Consultation;
import com.epam.hospital.model.treatment.type.CommunicationType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultationBuilder implements EntityBuilder<Consultation> {
    public Consultation build(ResultSet resultSet) throws SQLException {
        Consultation consultation = new Consultation();

        consultation.setConsultationId(resultSet.getInt(Column.CONSULTATION_ID));
        consultation.setCommunicationType(CommunicationType.valueOf(resultSet.getString(Column.CONSULTATION_COMMUNICATION_TYPE)));
        consultation.setDate(resultSet.getDate(Column.CONSULTATION_DATE));
        consultation.setDuration(resultSet.getInt(Column.CONSULTATION_DURATION));
        consultation.setDoctorId(resultSet.getInt(Column.CONSULTATION_DOCTOR_ID));
        consultation.setPatientId(resultSet.getInt(Column.CONSULTATION_PATIENT_ID));
        return consultation;
    }
}
