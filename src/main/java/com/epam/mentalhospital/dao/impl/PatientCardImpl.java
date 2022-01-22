package com.epam.mentalhospital.dao.impl;

import com.epam.mentalhospital.dao.PatientCardDao;
import com.epam.mentalhospital.dao.builder.BuilderFactory;
import com.epam.mentalhospital.dao.connectionpool.ConnectionPool;
import com.epam.mentalhospital.dao.connectionpool.exception.ConnectionPoolException;
import com.epam.mentalhospital.dao.exception.DaoException;
import com.epam.mentalhospital.dao.table_names.Column;
import com.epam.mentalhospital.dao.table_names.Table;
import com.epam.mentalhospital.model.treatment.PatientCard;

import java.sql.*;
import java.util.List;

public class PatientCardImpl extends AbstractDaoImpl<PatientCard> implements PatientCardDao {
    //private static final Logger LOGGER = LogManager.getLogger();
    //private static final ConnectionPool pool = new ConnectionPool();
    public static final String SAVE_OUTPATIENT_CARD_QUERY = String.format(
            "INSERT INTO %s (%s, %s, %s, %s) VALUES (?, ?, ?, ?)",
            Table.PATIENT_CARD_TABLE,
            Column.PATIENT_CARD_USER_ID,
            Column.PATIENT_CARD_SPARE_NUMBER,
            Column.PATIENT_CARD_AGE,
            Column.PATIENT_CARD_SEX
    );

    public static final String UPDATE_OUTPATIENT_CARD_QUERY = String.format(
            "UPDATE %s SET %s=?, %s=?, %s=?, %s=? WHERE %s=?",
            Table.PATIENT_CARD_TABLE,
            Column.PATIENT_CARD_USER_ID,
            Column.PATIENT_CARD_SPARE_NUMBER,
            Column.PATIENT_CARD_AGE,
            Column.PATIENT_CARD_SEX,
            Column.PATIENT_CARD_ID
    );

    public static final String FIND_BY_PATIENT_ID_QUERY = String.format(
            "SELECT * FROM %s WHERE %s=?",
            Table.PATIENT_CARD_TABLE,
            Column.PATIENT_CARD_USER_ID
    );

    public PatientCardImpl() {
        super(BuilderFactory.getPatientCardBuilder(), Table.PATIENT_CARD_TABLE, Column.PATIENT_CARD_ID);
    }

    @Override
    public void save(PatientCard entity) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(SAVE_OUTPATIENT_CARD_QUERY)) {
            setParams(entity, statement, SAVE_OUTPATIENT_CARD_QUERY);
            statement.executeUpdate();
        } catch (SQLException e) {
//            LOGGER.error("Unable to save new patientcard.",e);
            throw new DaoException(e);
        } catch (ConnectionPoolException e) {
//            LOGGER.error("Unable to get connection.",e);
            throw new DaoException("Unable to get connection.", e);
        }
    }

    @Override
    public void update(PatientCard entity) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_OUTPATIENT_CARD_QUERY)) {
            setParams(entity, statement, UPDATE_OUTPATIENT_CARD_QUERY);
            statement.executeUpdate();
        } catch (SQLException e) {
//            LOGGER.error("Can't update new patient card.",e);
            throw new DaoException("Can't update new patient card.", e);
        } catch (ConnectionPoolException e) {
//            LOGGER.error("Can't get connection.",e);
            throw new DaoException("Can't get connection.", e);
        }
    }

    @Override
    public PatientCard findByPatientId(int patientId) throws DaoException {
        PatientCard entity = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            statement = connection.prepareStatement(FIND_BY_PATIENT_ID_QUERY);
            statement.setInt(1, patientId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                entity = BuilderFactory.getPatientCardBuilder().build(resultSet);
            }
        } catch (SQLException e) {
//            LOGGER.error("Can't find entity by id.",e);
            throw new DaoException("Can't find by id.", e);
        } catch (ConnectionPoolException e) {
//            LOGGER.error("Can't get connection.",e);
            throw new DaoException("Unable to get connection.", e);
        }
        return entity;
    }

    @Override
    public List<PatientCard> findAll() throws DaoException {
        return super.findAll();
    }

    @Override
    public void deleteById(int id) throws DaoException {
        super.deleteById(id);
    }

    public void setParams(PatientCard card, PreparedStatement statement, String action) throws SQLException {
        statement.setInt(1, card.getUserId());
        statement.setString(2, card.getSpareNumber());
        statement.setInt(3, card.getAge());
        statement.setString(4, card.getSex());
        if (action.equals(UPDATE_OUTPATIENT_CARD_QUERY)) {
            statement.setInt(5, card.getCardId());
        }
    }

}
