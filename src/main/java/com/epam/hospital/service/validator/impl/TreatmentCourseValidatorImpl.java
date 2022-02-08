package com.epam.hospital.service.validator.impl;

import com.epam.hospital.model.treatment.TreatmentCourse;
import com.epam.hospital.service.validator.Validator;

public class TreatmentCourseValidatorImpl implements Validator<TreatmentCourse> {
    private static final int MAX_INSTRUCTION_SIZE = 500;
    private static final int MIN_ID_VALUE = 0;
    @Override
    public boolean isValid(TreatmentCourse entity) {
        String instruction = entity.getInstruction();
        int treatmetCourseId = entity.getTreatmentCourseId();
        if (instruction == null || instruction.length() > MAX_INSTRUCTION_SIZE){
            return false;
        }
        if (treatmetCourseId < MIN_ID_VALUE){
            return false;
        }
        return true;
    }
}
