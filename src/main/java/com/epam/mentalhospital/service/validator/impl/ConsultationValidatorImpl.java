package com.epam.mentalhospital.service.validator.impl;

import com.epam.mentalhospital.model.treatment.Consultation;
import com.epam.mentalhospital.service.validator.Validator;

public class ConsultationValidatorImpl implements Validator<Consultation> {
    @Override
    public boolean isValid(Consultation entity) {
        return false;
    }
}
