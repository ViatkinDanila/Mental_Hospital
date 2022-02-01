package com.epam.hospital.dao.impl;

import com.epam.hospital.dao.DiseaseDao;
import com.epam.hospital.dao.builder.BuilderFactory;
import com.epam.hospital.dao.exception.DaoException;
import com.epam.hospital.constant.database.Column;
import com.epam.hospital.constant.database.Table;
import com.epam.hospital.model.treatment.Disease;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DiseaseDaoImpl extends AbstractDaoImpl<Disease> implements DiseaseDao {
    private final static String SAVE_CONSULTATION_QUERY = String.format(
            "INSERT INTO %s (%s, %s) VALUES (?, ?)",
            Table.DISEASES_TABLE,
            Column.DISEASES_NAME,
            Column.DISEASES_DESCRIPTION
    );

    private final static String UPDATE_CONSULTATION_QUERY = String.format(
            "UPDATE %s SET %s=?, %s=? WHERE %s=?",
            Table.DISEASES_TABLE,
            Column.DISEASES_NAME,
            Column.DISEASES_DESCRIPTION,
            Column.DISEASES_ID
    );

    public DiseaseDaoImpl()  {
        super(BuilderFactory.getDiseaseBuilder(), Table.DISEASES_TABLE, Column.DISEASES_ID);
    }

    @Override
    public void save(Disease entity) throws DaoException {
        try (PreparedStatement statement = pooledConnection.prepareStatement(SAVE_CONSULTATION_QUERY);) {
            setParams(statement, entity, SAVE_CONSULTATION_QUERY);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Can't save disease.", e);
        }
    }

    @Override
    public void update(Disease entity) throws DaoException {
        try (PreparedStatement statement = pooledConnection.prepareStatement(UPDATE_CONSULTATION_QUERY);) {
            setParams(statement, entity, UPDATE_CONSULTATION_QUERY);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Can't update disease.", e);
        }
    }

    @Override
    public int findIdByName(String name) throws DaoException {
        return findByField(Column.DISEASES_NAME, name).get(0).getDiseaseId();
    }

    private void setParams(PreparedStatement statement, Disease disease, String action) throws SQLException {
        statement.setString(1, disease.getName());
        statement.setString(2, disease.getDescription());
        if (action.equals(UPDATE_CONSULTATION_QUERY)){
            statement.setInt(3, disease.getDiseaseId());
        }
    }
}
