package com.epam.hospital.service.validator.impl;

import com.epam.hospital.model.hospital.Chamber;
import com.epam.hospital.service.validator.Validator;
import jakarta.validation.Valid;


public class ChamberValidatorImpl implements Validator<Chamber> {
    private static final int MIN_ID_VALUE = 0;
    private static final int MIN_NUMBER_OF_FREE_BEDS = 0;
    private static final int MAX_NUMBER_OF_FREE_BEDS = 4;
    @Override
    public boolean isValid(Chamber entity) {
        int camberId = entity.getChamberId();
        int chamberTypeId = entity.getChamberTypeId();
        int hospitalId = entity.getHospitalId();
        int numberOfFreeBeds = entity.getNumberOfFreeBeds();

        if (camberId < MIN_ID_VALUE || chamberTypeId < MIN_ID_VALUE || hospitalId < MIN_ID_VALUE){
            return false;
        }

        if (numberOfFreeBeds < MIN_NUMBER_OF_FREE_BEDS || numberOfFreeBeds > MAX_NUMBER_OF_FREE_BEDS){
            return false;
        }
        return true;
    }
}
