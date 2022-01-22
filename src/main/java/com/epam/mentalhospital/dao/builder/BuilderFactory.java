package com.epam.mentalhospital.dao.builder;

import com.epam.mentalhospital.dao.builder.impl.*;
import com.epam.mentalhospital.model.treatment.Drug;

public class BuilderFactory {
    private final static UserBuilder userBuilder = new UserBuilder();
    private final static ChamberBuilder chamberBuilder = new ChamberBuilder();
    private final static ChamberTypeBuilder chamberTypeBuilder = new ChamberTypeBuilder();
    private final static ConsultationBuilder consultationBuilder = new ConsultationBuilder();
    private final static HospitalizationBuilder hospitalizationBuilder = new HospitalizationBuilder();
    private final static PatientCardBuilder patientCardBuilder = new PatientCardBuilder();
    private final static TreatmentCourseBuilder treatmentCourseBuilder = new TreatmentCourseBuilder();
    private final static DrugRecipeBuilder drugRecipeBuilder = new DrugRecipeBuilder();
    private final static DiseaseSymptomBuilder diseaseSymptomBuilder = new DiseaseSymptomBuilder();
    private final static DrugBuilder drugBuilder = new DrugBuilder();

    public static UserBuilder getUserBuilder() {
        return userBuilder;
    }

    public static ChamberBuilder getChamberBuilder() {
        return chamberBuilder;
    }

    public static ChamberTypeBuilder getChamberTypeBuilder() {
        return chamberTypeBuilder;
    }

    public static ConsultationBuilder getConsultationBuilder() {
        return consultationBuilder;
    }

    public static HospitalizationBuilder getHospitalizationBuilder() {
        return hospitalizationBuilder;
    }

    public static PatientCardBuilder getPatientCardBuilder() {
        return patientCardBuilder;
    }

    public static TreatmentCourseBuilder getTreatmentCourseBuilder() {
        return treatmentCourseBuilder;
    }

    public static DrugRecipeBuilder getDrugRecipeBuilder() {
        return drugRecipeBuilder;
    }

    public static DiseaseSymptomBuilder getDiseaseSymptomBuilder() {
        return diseaseSymptomBuilder;
    }

    public static DrugBuilder getDrugBuilder() {
        return drugBuilder;
    }
}
