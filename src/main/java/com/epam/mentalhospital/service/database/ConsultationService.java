package com.epam.mentalhospital.service.database;

import com.epam.mentalhospital.model.treatment.Consultation;

public interface ConsultationService {
    Consultation getConsultationById(int id);
}
