package com.epam.hospital.model.treatment;

import com.epam.hospital.model.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DrugRecipe implements Entity {
    private int treatmentCourseId;
    private int drugId;
    private int dose;
    private String description;

//    public int getTreatmentCourseId() {
//        return treatmentCourseId;
//    }
//
//    public void setTreatmentCourseId(int treatmentCourseId) {
//        this.treatmentCourseId = treatmentCourseId;
//    }
//
//    public int getDrugId() {
//        return drugId;
//    }
//
//    public void setDrugId(int drugId) {
//        this.drugId = drugId;
//    }
//
//    public int getDose() {
//        return dose;
//    }
//
//    public void setDose(int dose) {
//        this.dose = dose;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
}
