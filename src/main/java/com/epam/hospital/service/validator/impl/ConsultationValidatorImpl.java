package com.epam.hospital.service.validator.impl;

import com.epam.hospital.model.treatment.Consultation;
import com.epam.hospital.model.treatment.type.CommunicationType;
import com.epam.hospital.model.treatment.type.ConsultationStatus;
import com.epam.hospital.service.validator.Validator;

import java.sql.Timestamp;
import java.util.List;

public class ConsultationValidatorImpl implements Validator<Consultation> {
    private static final int MIN_ID_VALUE = 0;
    private static final List<String> INJECTION_SYMBOLS = List.of("$", "{", "}", "<", ">");

    @Override
    public boolean isValid(Consultation entity) {
        CommunicationType communicationType = entity.getCommunicationType();
        ConsultationStatus consultationStatus = entity.getStatus();
        int doctorId = entity.getDoctorId();
        int patientId = entity.getPatientId();

        if (    communicationType == null ||
                consultationStatus == null){
            return false;
        }

        if (!isValidOfInjectionAttack(communicationType.toString()) ||
                !isValidOfInjectionAttack(consultationStatus.toString())){
            return false;
        }

        if (doctorId < MIN_ID_VALUE || patientId < MIN_ID_VALUE){
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
