package com.epam.hospital.model.treatment;

import com.epam.hospital.model.Entity;
import com.epam.hospital.model.treatment.type.HospitalizationStatus;
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
public class Hospitalization implements Entity {
    private int hospitalizationId;
    private int patientId;
    private int doctorId;
    private HospitalizationStatus status;
}
