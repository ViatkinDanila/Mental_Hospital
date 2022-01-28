package com.epam.hospital.model.treatment;

import com.epam.hospital.model.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DiseaseSymptom implements Entity {
    private int treatmentCourseId;
    private int diseaseId;
    private String symptoms;
}
