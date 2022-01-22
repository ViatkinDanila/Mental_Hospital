package com.epam.hospital.service.database;

import com.epam.hospital.model.treatment.Consultation;

public interface ConsultationService {
    Consultation getConsultationById(int id);
}
