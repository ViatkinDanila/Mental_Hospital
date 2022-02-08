package com.epam.hospital.service.validator.impl;

import com.epam.hospital.model.treatment.DiseaseSymptom;
import com.epam.hospital.service.validator.Validator;

public class DiseaseSymptomValidatorImpl implements Validator<DiseaseSymptom> {
    private static final int MAX_SYMPTOMS_SIZE = 200;
    private static final int MIN_ID_VALUE = 1;

    @Override
    public boolean isValid(DiseaseSymptom entity) {
        String symptoms = entity.getSymptoms();
        int diseaseId = entity.getDiseaseId();

        if (symptoms == null || symptoms.length() > MAX_SYMPTOMS_SIZE){
            return false;
        }

        if (diseaseId < MIN_ID_VALUE){
            return false;
        }

        return true;
    }
}
