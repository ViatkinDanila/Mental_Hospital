package com.epam.mentalhospital.model.treatment;

import com.epam.mentalhospital.model.Entity;
import com.epam.mentalhospital.model.treatment.type.CommunicationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Consultation implements Entity {
    private int consultationId;
    private int doctorId;
    private int patientId;
    private CommunicationType communicationType;
    private String date;
    private int duration;
    private int treatmentCourseId;
}
