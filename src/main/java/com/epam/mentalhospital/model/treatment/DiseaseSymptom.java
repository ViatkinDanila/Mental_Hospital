package com.epam.mentalhospital.model.treatment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiseaseSymptom {
    private int treatmentCourseId;
    private int diseaseId;
    private String symptoms;
}
