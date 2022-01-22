package com.epam.hospital.dao.builder.impl;

import com.epam.hospital.dao.builder.EntityBuilder;
import com.epam.hospital.dao.table_names.Column;
import com.epam.hospital.model.treatment.DrugRecipe;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DrugRecipeBuilder implements EntityBuilder<DrugRecipe> {
    @Override
    public DrugRecipe build(ResultSet resultSet) throws SQLException {
        DrugRecipe drugRecipe = new DrugRecipe();
        drugRecipe.setDrugId(resultSet.getInt(Column.DRUG_RECIPE_DRUG_ID));
        drugRecipe.setTreatmentCourseId(resultSet.getInt(Column.DRUG_RECIPE_COURSE_ID));
        drugRecipe.setDose(resultSet.getInt(Column.DRUG_RECIPE_DOSE));
        drugRecipe.setDescription(resultSet.getString(Column.DRUG_RECIPE_DESCRIPTION));
        return drugRecipe;
    }
}