package com.epam.hospital.dao.impl;

import com.epam.hospital.dao.AbstractDao;
import com.epam.hospital.dao.DrugRecipeDao;
import com.epam.hospital.dao.builder.BuilderFactory;
import com.epam.hospital.dao.builder.EntityBuilder;
import com.epam.hospital.dao.exception.DaoException;
import com.epam.hospital.dao.table_names.Column;
import com.epam.hospital.dao.table_names.Table;
import com.epam.hospital.model.treatment.Drug;
import com.epam.hospital.model.treatment.DrugRecipe;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DrugRecipeDaoImpl extends AbstractDaoImpl<DrugRecipe> {
    private final static String SAVE_DRUG_RECIPE_QUERY = String.format(
            "INSERT INTO %s (%s, %s, %s, %s) VALUES (?, ?, ?, ?)",
            Table.DRUGS_RECIPES,
            Column.DRUGS_RECIPES_DESCRIPTION,
            Column.DRUGS_RECIPES_DOSE,
            Column.DRUGS_RECIPES_TREATMENT_COURSE_ID,
            Column.DRUGS_RECIPES_DRUG_ID
    );
    private final static String UPDATE_DRUG_RECIPE_QUERY = String.format(
            "UPDATE %s SET %s=?, %s=? WHERE %s=? AND %s=?",
            Table.DRUGS_RECIPES,
            Column.DRUGS_RECIPES_DESCRIPTION,
            Column.DRUGS_RECIPES_DOSE,
            Column.DRUGS_RECIPES_TREATMENT_COURSE_ID,
            Column.DRUGS_RECIPES_DRUG_ID
    );

    public DrugRecipeDaoImpl(){
        super(BuilderFactory.getDrugRecipeBuilder(), Table.DRUGS_RECIPES, Column.DRUGS_RECIPES_TREATMENT_COURSE_ID);
    }

    @Override
    public void save(DrugRecipe entity) throws DaoException {
        try (PreparedStatement statement = pooledConnection.prepareStatement(SAVE_DRUG_RECIPE_QUERY);) {
            setParams(statement, entity, SAVE_DRUG_RECIPE_QUERY);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Can't save drug recipe.", e);
        }
    }

    @Override
    public void update(DrugRecipe entity) throws DaoException {
        try (PreparedStatement statement = pooledConnection.prepareStatement(UPDATE_DRUG_RECIPE_QUERY);) {
            setParams(statement, entity, UPDATE_DRUG_RECIPE_QUERY);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Can't update drug recipe.", e);
        }
    }

    private void setParams(PreparedStatement statement, DrugRecipe drugRecipe, String action) throws SQLException{
        statement.setString(1, drugRecipe.getDescription());
        statement.setInt(2, drugRecipe.getDose());
        statement.setInt(3, drugRecipe.getTreatmentCourseId());
        statement.setInt(4, drugRecipe.getDrugId());
    }
}
