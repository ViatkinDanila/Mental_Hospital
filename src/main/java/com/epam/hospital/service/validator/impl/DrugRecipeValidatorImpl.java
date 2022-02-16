package com.epam.hospital.service.validator.impl;

import com.epam.hospital.model.treatment.DrugRecipe;
import com.epam.hospital.service.validator.Validator;

import java.util.List;

public class DrugRecipeValidatorImpl implements Validator<DrugRecipe> {
    private static final int MAX_DESCRIPTION_SIZE = 500;
    private static final int MIN_ID = 1;
    private static final int MIN_DOSE = 1;
    private static final List<String> INJECTION_SYMBOLS = List.of("$", "{", "}", "<", ">");

    @Override
    public boolean isValid(DrugRecipe entity) {
        int drugId = entity.getDrugId();
        int treatmentCourseId = entity.getTreatmentCourseId();
        float dose = entity.getDose();
        String description = entity.getDescription();

        if (drugId < MIN_ID || treatmentCourseId < MIN_ID){
            return false;
        }

        if (dose < MIN_DOSE){
            return false;
        }

        if (description == null || description.length() > MAX_DESCRIPTION_SIZE){
            return false;
        }

        if (!isValidOfInjectionAttack(description)){
            return false;
        }
        return true;
    }

    private boolean isValidOfInjectionAttack(String line) {
        for (String injectSymbol : INJECTION_SYMBOLS) {
            if (line.contains(injectSymbol)) {
                return false;
            }
        }
        return true;
    }
}
