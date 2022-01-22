package com.epam.hospital.service.validator.impl;

import com.epam.hospital.model.treatment.Consultation;
import com.epam.hospital.service.validator.Validator;

public class ConsultationValidatorImpl implements Validator<Consultation> {
    @Override
    public boolean isValid(Consultation entity) {
        return false;
    }
}
