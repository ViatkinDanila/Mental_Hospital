package com.epam.hospital.service.validator.impl;

import com.epam.hospital.model.user.info.DoctorInfo;
import com.epam.hospital.service.validator.Validator;


public class DoctorInfoValidatorImpl implements Validator<DoctorInfo> {
    private static final int MIN_CLASSIFICATION_RANK = 1;
    private static final int MAX_CLASSIFICATION_RANK = 5;
    private static final int MAX_SPECIALIZATION_LENGTH = 45;
    private static final int MIN_WORK_EXPERIENCE = 4;
    private static final int MIN_ID_VALUE = 1;

    @Override
    public boolean isValid(DoctorInfo info) {
        String specialization = info.getSpecialization();
        int wordExperience = info.getWorkExperience();
        int classification = info.getClassification();
        int doctorId = info.getDoctorId();
        if (specialization == null || specialization.length() > MAX_SPECIALIZATION_LENGTH){
            return false;
        }

        if (wordExperience < MIN_WORK_EXPERIENCE){
            return false;
        }

        if (classification > MAX_CLASSIFICATION_RANK || classification < MIN_CLASSIFICATION_RANK){
            return false;
        }

        if (doctorId < MIN_ID_VALUE){
            return false;
        }
        return true;
    }
}
