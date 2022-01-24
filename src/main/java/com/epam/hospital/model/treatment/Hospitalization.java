package com.epam.hospital.model.treatment;

import com.epam.hospital.model.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Objects;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hospitalization implements Entity {
    private int hospitalizationId;
    private int patientId;
    private boolean status;
}
