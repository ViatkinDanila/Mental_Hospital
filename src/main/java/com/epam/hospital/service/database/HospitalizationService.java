package com.epam.hospital.service.database;

import com.epam.hospital.model.treatment.ChamberStaying;
import com.epam.hospital.model.treatment.Hospitalization;
import com.epam.hospital.service.exception.ServiceException;

import java.util.List;

public interface HospitalizationService {
    Hospitalization getHospitalizationById(int hospitalizationId) throws ServiceException;
    List<Hospitalization> getAllHospitalizationsByPatientCardId(int patientCardId) throws ServiceException;
    List<Hospitalization> getAllHospitalizationsByDoctorCardId(int patientCardId) throws ServiceException;
    void saveHospitalization(Hospitalization hospitalization, ChamberStaying chamberStaying) throws ServiceException;
    void update(Hospitalization hospitalization) throws ServiceException;
    void updateChamberStaying(ChamberStaying chamberStaying) throws ServiceException;

}
