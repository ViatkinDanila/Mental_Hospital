package com.epam.hospital.service.validator.impl;

import com.epam.hospital.model.treatment.Drug;
import com.epam.hospital.service.validator.Validator;

public class DrugValidatorImpl implements Validator<Drug> {
    private static final int MAX_TITLE_LENGTH = 45;
    private static final int MIN_ID_VALUE = 0;

    @Override
    public boolean isValid(Drug entity) {
        String title = entity.getName();
        int drugId = entity.getDrugId();
        if (title == null || title.length() > MAX_TITLE_LENGTH){
            return false;
        }

        if (drugId < MIN_ID_VALUE){
            return false;
        }
        return true;
    }
}
