package com.epam.hospital.service.validator.impl;

import com.epam.hospital.model.treatment.ChamberStaying;
import com.epam.hospital.service.validator.Validator;

public class ChamberStayingValidatorImpl implements Validator<ChamberStaying> {
    private static final int MIN_ID_VALUE = 1;
    @Override
    public boolean isValid(ChamberStaying entity) {
        int hospitalizationId = entity.getHospitalizationId();
        int chamberId = entity.getChamberId();

        if (hospitalizationId < MIN_ID_VALUE || chamberId < MIN_ID_VALUE  ){
            return false;
        }
        return true;
    }
}
