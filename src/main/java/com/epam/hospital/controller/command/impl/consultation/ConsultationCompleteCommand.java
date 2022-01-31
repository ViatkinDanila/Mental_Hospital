package com.epam.hospital.controller.command.impl.consultation;

import com.epam.hospital.controller.command.Command;
import com.epam.hospital.controller.command.CommandResult;
import com.epam.hospital.controller.command.util.ParameterExtractor;
import com.epam.hospital.controller.constant.CommandName;
import com.epam.hospital.controller.request.RequestContext;
import com.epam.hospital.model.treatment.Consultation;
import com.epam.hospital.model.treatment.DiseaseSymptom;
import com.epam.hospital.model.treatment.DrugRecipe;
import com.epam.hospital.model.treatment.TreatmentCourse;
import com.epam.hospital.model.treatment.type.ConsultationStatus;
import com.epam.hospital.service.database.ConsultationService;
import com.epam.hospital.service.database.DiseaseService;
import com.epam.hospital.service.database.DrugService;
import com.epam.hospital.service.database.TreatmentCourseService;
import com.epam.hospital.service.database.impl.ConsultationServiceImpl;
import com.epam.hospital.service.database.impl.DiseaseServiceImpl;
import com.epam.hospital.service.database.impl.DrugServiceImpl;
import com.epam.hospital.service.database.impl.TreatmentCourseServiceImpl;
import com.epam.hospital.service.exception.ServiceException;
import com.epam.hospital.util.constant.Parameter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
//TESTED
@Slf4j
public class ConsultationCompleteCommand implements Command {
    private static final ConsultationService consultationService = ConsultationServiceImpl.getInstance();
    private static final TreatmentCourseService treatmentCourseService = TreatmentCourseServiceImpl.getInstance();
    private static final DiseaseService diseaseService = DiseaseServiceImpl.getInstance();
    private static final DrugService drugService = DrugServiceImpl.getInstance();
    private static final String CONSULTATION_PAGE_COMMAND = "MentalHospital?command=" + CommandName.CONSULTATION_PAGE ;
    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {

        List<String> diseasesNames = getStringList(Parameter.DISEASE, requestContext);

        List<Integer> diseasesId = new ArrayList<Integer>();
        for (String diseaseName : diseasesNames){
            int diseaseId = diseaseService.getDiseaseIdByName(diseaseName);
            diseasesId.add(diseaseId);
        }

        List<String> symptoms = getStringList(Parameter.SYMPTOMS, requestContext);

        List<DiseaseSymptom> diseaseSymptoms = new ArrayList<DiseaseSymptom>();
        for(int i = 0; i < symptoms.size() && i < diseasesId.size(); i++){
            DiseaseSymptom diseaseSymptom = DiseaseSymptom.builder()
                    .diseaseId(diseasesId.get(i))
                    .symptoms(symptoms.get(i))
                    .build();
            diseaseSymptoms.add(diseaseSymptom);
        }

        List<DrugRecipe> drugsRecipes = new ArrayList<>();
        List<String> drugsNames = getStringList(Parameter.DRUG, requestContext);
        if (drugsNames.size() > 0) {

            List<Integer> drugsId = new ArrayList<>();
            for (String drugName : drugsNames) {
                int drugId = drugService.getDrugIdByName(drugName);
                drugsId.add(drugId);
            }

            List<String> descriptions = getStringList(Parameter.DESCRIPTION, requestContext);

            List<Integer> doses = new ArrayList<>();
            List<String> dosesListStr = getStringList(Parameter.DOSE, requestContext);
            for (String dose : dosesListStr) {
                doses.add(Integer.parseInt(dose));
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
                .instruction(ParameterExtractor.extractString(Parameter.INSTRUCTION, requestContext))
                .build();

        int treatmentCourseId = treatmentCourseService.saveTreatmentCourse(treatmentCourse, diseaseSymptoms, drugsRecipes);
        int consultationId = ParameterExtractor.extractInt(Parameter.CONSULTATION_ID, requestContext);
        Consultation consultation = consultationService.getConsultationById(consultationId);
        consultation.setTreatmentCourseId(treatmentCourseId);
        consultation.setStatus(ConsultationStatus.COMPLETED);
        consultationService.update(consultation);

        return CommandResult.redirect(CONSULTATION_PAGE_COMMAND + "&id=" + consultationId);
    }

    private List<String> getStringList(String parameterName, RequestContext requestContext){
        parameterName += "-";
        List<String> parameters = new ArrayList<String>();
        String parameter = "";
        for (int i = 0; ; i++){
            parameter = ParameterExtractor.extractString(parameterName+i,requestContext);
            if (parameter != null){
                parameters.add(parameter);
            } else {
                break;
            }
        }
        return parameters;
    }

}
