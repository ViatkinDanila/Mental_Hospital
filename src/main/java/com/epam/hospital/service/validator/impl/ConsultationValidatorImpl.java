package com.epam.hospital.service.validator.impl;

import com.epam.hospital.model.treatment.Consultation;
import com.epam.hospital.model.treatment.type.CommunicationType;
import com.epam.hospital.model.treatment.type.ConsultationStatus;
import com.epam.hospital.service.validator.Validator;

import java.sql.Timestamp;

public class ConsultationValidatorImpl implements Validator<Consultation> {
    private static final int MIN_ID_VALUE = 0;
    private static final int MIN_DURATION_VALUE = 10;
    private static final int MAX_DURATION_VALUE = 60;


    @Override
    public boolean isValid(Consultation entity) {
        CommunicationType communicationType = entity.getCommunicationType();
        ConsultationStatus consultationStatus = entity.getStatus();
        int doctorId = entity.getDoctorId();
        int patientId = entity.getPatientId();
        int treatmentCourseId = entity.getTreatmentCourseId();
        int duration = entity.getDuration();

        if (    communicationType == null ||
                consultationStatus == null){
            return false;
        }

        if (doctorId < MIN_ID_VALUE || patientId < MIN_ID_VALUE || treatmentCourseId < MIN_ID_VALUE ){
            return false;
        }

        if (duration < MIN_DURATION_VALUE || duration > MAX_DURATION_VALUE){
            return false;
        }
        return true;
    }
}
