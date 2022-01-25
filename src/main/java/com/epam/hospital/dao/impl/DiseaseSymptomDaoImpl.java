package com.epam.hospital.dao.impl;

import com.epam.hospital.dao.DiseaseSymptomDao;
import com.epam.hospital.dao.builder.BuilderFactory;
import com.epam.hospital.dao.builder.EntityBuilder;
import com.epam.hospital.dao.exception.DaoException;
import com.epam.hospital.dao.table_names.Column;
import com.epam.hospital.dao.table_names.Table;
import com.epam.hospital.model.treatment.DiseaseSymptom;
import com.epam.hospital.model.treatment.DrugRecipe;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DiseaseSymptomDaoImpl extends AbstractDaoImpl<DiseaseSymptom> {
    private final static String SAVE_DISEASE_SYMPTOMS_QUERY = String.format(
            "INSERT INTO %s (%s, %s, %s) VALUES (?, ?, ?)",
            Table.DISEASE_SYMPTOMS_TABLE,
            Column.DISEASE_SYMPTOMS_SYMPTOMS,
            Column.DISEASE_SYMPTOMS_COURSE_ID,
            Column.DISEASE_SYMPTOMS_DISEASE_ID
    );
    private final static String UPDATE_DISEASE_SYMPTOMS_QUERY = String.format(
            "UPDATE %s SET %s=? WHERE %s=? AND %s=?",
            Table.DISEASE_SYMPTOMS_TABLE,
            Column.DISEASE_SYMPTOMS_SYMPTOMS,
            Column.DISEASE_SYMPTOMS_COURSE_ID,
            Column.DISEASE_SYMPTOMS_DISEASE_ID
    );

    private final static String FIND_DISEASE_SYMPTOMS_QUERY = String.format(
            "SELECT * FROM %s WHERE %s=? AND %s=?",
            Table.DISEASE_SYMPTOMS_TABLE,
            Column.DISEASE_SYMPTOMS_COURSE_ID,
            Column.DISEASE_SYMPTOMS_DISEASE_ID
    );

    public DiseaseSymptomDaoImpl(){
        super(BuilderFactory.getDiseaseSymptomBuilder(), Table.DISEASE_SYMPTOMS_TABLE, Column.DISEASE_SYMPTOMS_COURSE_ID);
    }

    @Override
    public void save(DiseaseSymptom entity) throws DaoException {
        try (PreparedStatement statement = pooledConnection.prepareStatement(SAVE_DISEASE_SYMPTOMS_QUERY);) {
            setParams(statement, entity, SAVE_DISEASE_SYMPTOMS_QUERY);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Can't save disease symptom.", e);
        }
    }

    @Override
    public void update(DiseaseSymptom entity) throws DaoException {
        try (PreparedStatement statement = pooledConnection.prepareStatement(UPDATE_DISEASE_SYMPTOMS_QUERY);) {
            setParams(statement, entity, UPDATE_DISEASE_SYMPTOMS_QUERY);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Can't update disease symptom.", e);
        }
    }

    @Override
    public DiseaseSymptom findById(int... ids) throws DaoException{
        DiseaseSymptom entity = null;
        try (PreparedStatement statement = pooledConnection.prepareStatement(FIND_DISEASE_SYMPTOMS_QUERY);) {
            statement.setObject(1, ids[0]);
            statement.setObject(2, ids[1]);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                entity = BuilderFactory.getDiseaseSymptomBuilder().build(resultSet);
            }
        } catch (SQLException e) {
//            LOGGER.error("Can't find entity by id.",e);
            throw new DaoException("Can't disease symptom find by id.", e);
        }
        return entity;
    }

    private void setParams(PreparedStatement statement, DiseaseSymptom diseaseSymptom, String action) throws SQLException {
        statement.setString(1, diseaseSymptom.getSymptoms());
        statement.setInt(2, diseaseSymptom.getTreatmentCourseId());
        statement.setInt(3, diseaseSymptom.getDiseaseId());
    }
}
