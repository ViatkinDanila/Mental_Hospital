package com.epam.hospital.controller.command.impl;

import com.epam.hospital.controller.command.Command;
import com.epam.hospital.controller.command.CommandResult;
import com.epam.hospital.controller.constant.Page;
import com.epam.hospital.controller.request.RequestContext;
import com.epam.hospital.dao.table_names.Column;
import com.epam.hospital.model.dto.ConsultationDto;
import com.epam.hospital.model.treatment.*;
import com.epam.hospital.model.user.User;
import com.epam.hospital.service.database.*;
import com.epam.hospital.service.database.impl.*;
import com.epam.hospital.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class ConsultationPageCommand implements Command {
    private final static ConsultationService consultationService = ConsultationServiceImpl.getInstance();
    private final static TreatmentCourseService treatmentCourseService = TreatmentCourseServiceImpl.getInstance();
    private final static UserService userService = UserServiceImpl.getInstance();
    private final static PatientCardService patientCardService = PatientCardServiceImpl.getInstance();
    private final static DiseaseService diseaseService = DiseaseServiceImpl.getInstance();
    private final static DrugService drugService = DrugServiceImpl.getInstance();

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {

        String consultationIdString = requestContext.getRequestParameter(Column.ID);
        int consultationId = Integer.parseInt(consultationIdString);

        Consultation consultation = consultationService.getConsultationById(consultationId);
        TreatmentCourse treatmentCourse = treatmentCourseService.getTreatmentCourseById(consultation.getTreatmentCourseId());
        User doctor = userService.getUserById(consultation.getDoctorId());
        int userId = patientCardService.getPatientCardById(consultation.getPatientId()).getUserId();
        User user = userService.getUserById(userId);

        List<DiseaseSymptom> diseaseSymptoms = treatmentCourseService.getDiseaseSymptoms(treatmentCourse.getTreatmentCourseId());
        List<String> diseases = new ArrayList<>();
        for (DiseaseSymptom diseaseSymptom : diseaseSymptoms) {
            int diseaseId = diseaseSymptom.getDiseaseId();
            Disease diseaseById = diseaseService.getDiseaseById(diseaseId);
            String name = diseaseById.getName();
            diseases.add(name);
        }

        List<DrugRecipe> drugRecipes = treatmentCourseService.getDrugRecipes(treatmentCourse.getTreatmentCourseId());
        List<String> drugs = new ArrayList<>();
        for (DrugRecipe drugRecipe : drugRecipes) {
            int drugId = drugRecipe.getDrugId();
            Drug drugById = drugService.getDrugById(drugId);
            String name = drugById.getName();
            drugs.add(name);
        }

        ConsultationDto consultationDto = ConsultationDto.builder()
                .consultationId(consultationId)
                .communicationType(consultation.getCommunicationType().toString())
                .date(consultation.getDate())
                .duration(consultation.getDuration())
                .doctorFirstName(doctor.getFirstname())
                .doctorLastName(doctor.getLastname())
                .patientFirstName(user.getFirstname())
                .patientLastName(user.getLastname())
                .diseases(diseases)
                .drugs(drugs)
                .instruction(treatmentCourse.getInstruction())
                .build();

        requestContext.addAttribute("consultation", consultationDto);
        return CommandResult.forward(Page.CONSULTATION_PAGE);
    }
}
