package com.epam.hospital.controller.command.impl;

import com.epam.hospital.controller.command.Command;
import com.epam.hospital.controller.command.CommandResult;
import com.epam.hospital.controller.command.util.ParameterExtractor;
import com.epam.hospital.controller.constant.CommandName;
import com.epam.hospital.controller.request.RequestContext;
import com.epam.hospital.model.treatment.Consultation;
import com.epam.hospital.model.treatment.DiseaseSymptom;
import com.epam.hospital.model.treatment.DrugRecipe;
import com.epam.hospital.model.treatment.TreatmentCourse;
import com.epam.hospital.service.database.ConsultationService;
import com.epam.hospital.service.database.DiseaseService;
import com.epam.hospital.service.database.DrugService;
import com.epam.hospital.service.database.TreatmentCourseService;
import com.epam.hospital.service.database.impl.ConsultationServiceImpl;
import com.epam.hospital.service.database.impl.DiseaseServiceImpl;
import com.epam.hospital.service.database.impl.DrugServiceImpl;
import com.epam.hospital.service.database.impl.TreatmentCourseServiceImpl;
import com.epam.hospital.service.exception.ServiceException;
import com.epam.hospital.util.constant.Attribute;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConsultationCompleteCommand implements Command {
    private static final ConsultationService consultationService = ConsultationServiceImpl.getInstance();
    private static final TreatmentCourseService treatmentCourseService = TreatmentCourseServiceImpl.getInstance();
    private static final DiseaseService diseaseService = DiseaseServiceImpl.getInstance();
    private static final DrugService drugService = DrugServiceImpl.getInstance();
    private static final String PROFILE_PAGE_COMMAND = "MentalHospital?command=" + CommandName.PROFILE_PAGE;
    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {

        //TODO replace attribute to parameter
        String diseasesNamesStr = ParameterExtractor.extractString(Attribute.DISEASES_NAMES, requestContext);
        diseasesNamesStr.replaceAll(" ","");
        List<String> diseasesNames = new ArrayList<String>();
        //TODO decompozition
        if (diseasesNamesStr.contains(",")){
            diseasesNames = Arrays.asList(diseasesNamesStr.split(","));
        } else {
            diseasesNames.add(diseasesNamesStr);
        }
        List<Integer> diseasesId = new ArrayList<>();
        for (String diseaseName : diseasesNames){
            int diseaseId = diseaseService.getDiseaseIdByName(diseaseName);
            diseasesId.add(diseaseId);
        }

        String symptomsStr = ParameterExtractor.extractString(Attribute.SYMPTOMS, requestContext);
        symptomsStr.replaceAll(""," ");
        List<String> symptoms = new ArrayList<String>();
        if (symptomsStr.contains(";")){
            symptoms = Arrays.asList(symptomsStr.split(";"));
        } else {
            symptoms.add(symptomsStr);
        }

        List<DiseaseSymptom> diseaseSymptoms = new ArrayList<DiseaseSymptom>();
        for(int i = 0; i < symptoms.size() && i < diseasesId.size(); i++){
            DiseaseSymptom diseaseSymptom = DiseaseSymptom.builder()
                    .diseaseId(diseasesId.get(i))
                    .symptoms(symptoms.get(i))
                    .build();
            diseaseSymptoms.add(diseaseSymptom);
        }

        String drugsNamesStr = ParameterExtractor.extractString(Attribute.DRUGS_NAMES, requestContext);
        drugsNamesStr.replaceAll(""," ");
        List<DrugRecipe> drugsRecipes = new ArrayList<DrugRecipe>();
        if (drugsNamesStr != null) {
            List<String> drugsNames = new ArrayList<String>();
            if (drugsNamesStr.contains(",")) {
                drugsNames = Arrays.asList(drugsNamesStr.split(","));
            } else {
                drugsNames.add(drugsNamesStr);
            }
            List<Integer> drugsId = new ArrayList<>();
            for (String drugName : drugsNames) {
                int drugId = drugService.getDrugIdByName(drugName);
                drugsId.add(drugId);
            }
            String descriptionsStr = ParameterExtractor.extractString(Attribute.DESCRIPTIONS, requestContext);
            List<String> descriptions = new ArrayList<String>();
            if (descriptionsStr.contains(",")) {
                descriptions = Arrays.asList(descriptionsStr.split(","));
            } else {
                descriptions.add(descriptionsStr);
            }

            String dosesStr = ParameterExtractor.extractString(Attribute.DOSES, requestContext).replace(" ","");
            List<Integer> doses = new ArrayList<Integer>();
            List<String> dosesListStr = new ArrayList<String>();
            if (dosesStr.contains(",")) {
                dosesListStr = Arrays.asList(dosesStr.split(","));
                for (String doseStr : dosesListStr) {
                    doses.add(Integer.parseInt(doseStr));
                }
            } else {
                doses.add(Integer.parseInt(dosesStr));
            }

            for (int i = 0; i < drugsId.size() && i < descriptions.size() && i < doses.size(); i++) {
                DrugRecipe drugRecipe = DrugRecipe.builder()
                        .drugId(drugsId.get(i))
                        .description(descriptions.get(i))
                        .dose(doses.get(i))
                        .build();
                drugsRecipes.add(drugRecipe);
            }
        }

        TreatmentCourse treatmentCourse = TreatmentCourse.builder()
                .instruction(ParameterExtractor.extractString(Attribute.INSTRUCTION, requestContext))
                .build();

        int treatmentCourseId = treatmentCourseService.saveTreatmentCourse(treatmentCourse, diseaseSymptoms, drugsRecipes);
        int consultationId = ParameterExtractor.extractInt(Attribute.CONSULTATION_ID, requestContext);
        Consultation consultation = consultationService.getConsultationById(consultationId);
        consultation.setTreatmentCourseId(treatmentCourseId);
        return CommandResult.redirect(PROFILE_PAGE_COMMAND);
    }
}
