package com.epam.mentalhospital.model.treatment;

import com.epam.mentalhospital.model.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreatmentCourse implements Entity {
    private int treatmentCourseId;
    private String instruction;
}
