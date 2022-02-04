package com.epam.hospital.model.treatment;

import com.epam.hospital.model.Entity;
import com.epam.hospital.model.treatment.type.CommunicationType;
import com.epam.hospital.model.treatment.type.ConsultationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Consultation implements Entity {
    //TODO онли ссылочные типы
     int consultationId;
     int doctorId;
     int patientId;
     CommunicationType communicationType;
     Date date;
     int duration;
     Integer treatmentCourseId;
     double price;
     ConsultationStatus status;
}
