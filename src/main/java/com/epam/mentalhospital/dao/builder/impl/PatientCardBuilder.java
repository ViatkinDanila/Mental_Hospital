package com.epam.mentalhospital.dao.builder.impl;

import com.epam.mentalhospital.dao.builder.EntityBuilder;
import com.epam.mentalhospital.dao.table_names.Column;
import com.epam.mentalhospital.model.treatment.PatientCard;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PatientCardBuilder implements EntityBuilder<PatientCard> {
    public PatientCard build(ResultSet resultSet) throws SQLException {
        PatientCard outpatientCard = new PatientCard();
        outpatientCard.setCardId(resultSet.getInt(Column.PATIENT_CARD_ID));
        outpatientCard.setSpareNumber(resultSet.getString(Column.PATIENT_CARD_SPARE_NUMBER));
        outpatientCard.setAge(resultSet.getInt(Column.PATIENT_CARD_AGE));
        outpatientCard.setUserId(resultSet.getInt(Column.PATIENT_CARD_USER_ID));
        outpatientCard.setSex(resultSet.getString(Column.PATIENT_CARD_SEX));
        return outpatientCard;
    }
}
