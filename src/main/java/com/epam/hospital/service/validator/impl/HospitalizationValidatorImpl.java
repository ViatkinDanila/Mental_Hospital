package com.epam.hospital.service.validator.impl;

import com.epam.hospital.model.treatment.Hospitalization;
import com.epam.hospital.service.validator.Validator;

public class HospitalizationValidatorImpl implements Validator<Hospitalization> {
    private static final int MIN_ID_VALUE = 0;
    @Override
    public boolean isValid(Hospitalization entity) {
        int patientId = entity.getPatientId();
        int doctorId = entity.getDoctorId();
        int id = entity.getId();

        if (patientId < MIN_ID_VALUE || doctorId < MIN_ID_VALUE || id < MIN_ID_VALUE ){
            return false;
        }
        return true;
    }
}
