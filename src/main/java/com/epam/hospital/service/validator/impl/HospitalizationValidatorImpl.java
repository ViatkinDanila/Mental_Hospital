package com.epam.hospital.service.validator.impl;

import com.epam.hospital.model.treatment.Hospitalization;
import com.epam.hospital.model.treatment.type.HospitalizationStatus;
import com.epam.hospital.service.validator.Validator;

import java.util.List;

public class HospitalizationValidatorImpl implements Validator<Hospitalization> {
    private static final int MIN_ID_VALUE = 1;
    private static final List<String> INJECTION_SYMBOLS = List.of("$", "{", "}", "<", ">");

    @Override
    public boolean isValid(Hospitalization entity) {
        HospitalizationStatus hospitalizationStatus = entity.getStatus();
        int patientId = entity.getPatientId();
        int doctorId = entity.getDoctorId();

        if (hospitalizationStatus == null){
            return false;
        }

        if (!isValidOfInjectionAttack(hospitalizationStatus.toString())){
            return false;
        }

        if (patientId < MIN_ID_VALUE || doctorId < MIN_ID_VALUE){
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
