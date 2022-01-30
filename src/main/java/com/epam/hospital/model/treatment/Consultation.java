package com.epam.hospital.model.treatment;

import com.epam.hospital.model.Entity;
import com.epam.hospital.model.treatment.type.CommunicationType;
import com.epam.hospital.model.treatment.type.ConsultationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Consultation implements Entity {
    //TODO онли ссылочные типы
    private int consultationId;
    private int doctorId;
    private int patientId;
    private CommunicationType communicationType;
    private Date date;
    private int duration;
    private Integer treatmentCourseId;
    private double price;
    private ConsultationStatus status;
}
