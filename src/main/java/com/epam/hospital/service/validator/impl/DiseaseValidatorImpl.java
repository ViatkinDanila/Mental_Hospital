package com.epam.hospital.service.validator.impl;

import com.epam.hospital.model.treatment.Disease;
import com.epam.hospital.service.validator.Validator;

public class DiseaseValidatorImpl implements Validator<Disease> {
    private static final int MAX_NAME_LENGTH = 500;
    private static final int MAX_DESCRIPTION_LENGTH = 500;
    private static final int MIN_ID_VALUE = 0;

    @Override
    public boolean isValid(Disease entity) {
        String name = entity.getName();
        String description = entity.getDescription();
        int diseaseId = entity.getDiseaseId();
        if (name == null || name.length() > MAX_NAME_LENGTH){
            return false;
        }

        if (description == null || description.length() > MAX_DESCRIPTION_LENGTH){
            return false;
        }

        if (diseaseId < MIN_ID_VALUE){
            return false;
        }
        return true;
    }
}
