package com.epam.hospital.service.validator.impl;

import com.epam.hospital.model.treatment.PatientCard;
import com.epam.hospital.service.validator.Validator;

public class PatientCardValidatorImpl implements Validator<PatientCard> {
    private static final int MAX_SEX_LENGTH = 6;
    private static final int MAX_SPARE_NUMBER_LENGTH = 45;
    private static final int MIN_AGE = 18;
    private static final String MALE = "male";
    private static final String FEMALE = "female";
    private static final int MIN_ID_VALUE = 0;

    @Override
    public boolean isValid(PatientCard entity) {
        String spareNumber = entity.getSpareNumber();
        int age = entity.getAge();
        String sex = entity.getSex();
        int patientCardId = entity.getCardId();
        if (spareNumber == null || spareNumber.length() > MAX_SPARE_NUMBER_LENGTH){
            return false;
        }

        if (age < MIN_AGE){
            return false;
        }

        if (!(sex.equals(MALE) || sex.equals(FEMALE)) || sex.length() > MAX_SEX_LENGTH){
            return false;
        }

        if (patientCardId < MIN_ID_VALUE){
            return false;
        }
        return true;
    }
}
