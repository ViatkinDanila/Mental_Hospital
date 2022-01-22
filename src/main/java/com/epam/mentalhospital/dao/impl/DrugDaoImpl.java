package com.epam.mentalhospital.dao.impl;

import com.epam.mentalhospital.dao.AbstractDao;
import com.epam.mentalhospital.dao.builder.BuilderFactory;
import com.epam.mentalhospital.dao.exception.DaoException;
import com.epam.mentalhospital.dao.table_names.Column;
import com.epam.mentalhospital.dao.table_names.Table;
import com.epam.mentalhospital.model.Entity;
import com.epam.mentalhospital.model.treatment.Drug;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DrugDaoImpl extends AbstractDaoImpl<Drug> {
    private final static String SAVE_DRUG_QUERY = String.format(
            "INSERT INTO %s %s VALUES ?",
            Table.DRUGS_TABLE,
            Column.DRUGS_NAME
            );
    private final static String UPDATE_DRUG_QUERY = String.format(
            "UPDATE %s SET %s=? WHERE %s=?",
            Table.DRUGS_TABLE,
            Column.DRUGS_NAME,
            Column.DRUGS_ID
    );
    public DrugDaoImpl() {
        super(BuilderFactory.getDrugBuilder(), Table.DRUGS_TABLE, Column.DRUGS_ID);
    }

    @Override
    public void save(Drug entity) throws DaoException {
        try(PreparedStatement statement = pooledConnection.prepareStatement(SAVE_DRUG_QUERY);){
            setParams(statement, entity, SAVE_DRUG_QUERY);
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new DaoException("Can't save drug.", e);
        }
    }

    @Override
    public void update(Drug entity) throws DaoException {
        try(PreparedStatement statement = pooledConnection.prepareStatement(UPDATE_DRUG_QUERY);){
            setParams(statement, entity, UPDATE_DRUG_QUERY);
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new DaoException("Can't update drug.", e);
        }
    }

    private void setParams(PreparedStatement statement, Drug drug, String action) throws SQLException{
        statement.setString(1, drug.getName());
        if (action.equals(UPDATE_DRUG_QUERY)){
            statement.setInt(2,drug.getDrugId());

        }
    }
}