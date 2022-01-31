package com.epam.hospital.controller.command.impl;

import com.epam.hospital.controller.command.Command;
import com.epam.hospital.controller.command.CommandResult;
import com.epam.hospital.controller.command.util.ParameterExtractor;
import com.epam.hospital.controller.constant.Page;
import com.epam.hospital.controller.request.RequestContext;
import com.epam.hospital.dao.table_names.Column;
import com.epam.hospital.model.dto.ConsultationDto;
import com.epam.hospital.model.dto.DiseaseWithSymptomsDto;
import com.epam.hospital.model.dto.DrugRecipeDto;
import com.epam.hospital.model.treatment.*;
import com.epam.hospital.model.treatment.type.CommunicationType;
import com.epam.hospital.model.treatment.type.ConsultationStatus;
import com.epam.hospital.model.user.User;
import com.epam.hospital.service.database.*;
import com.epam.hospital.service.database.impl.*;
import com.epam.hospital.service.exception.ServiceException;
import com.epam.hospital.util.constant.Attribute;
import com.epam.hospital.util.constant.Parameter;

import java.util.ArrayList;
import java.util.Date;
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
//        if (true) {
//            ConsultationDto consultationDto = ConsultationDto.builder()
//                    .duration(10)
//                    .communicationType("ONLINE")
//                    .date(new Date())
//                    .diseases(List.of(
//                            DiseaseWithSymptomsDto.builder().name("RAK MOZGA").id(1).symptoms("GOLOVeshka bolit o4en. Dota 2 player.").build(),
//                            DiseaseWithSymptomsDto.builder().name("ICE ICE BABY").id(2).symptoms("Holodno pizdaaaaaa. Eshe net vozdyha.").build()
//                    ))
//                    .patientFirstName("Edik")
//                    .patientLastName("Pidobir")
//                    .doctorFirstName("Alex")
//                    .doctorLastName("Voroshilov")
//                    .consultationStatus(ConsultationStatus.PENDING)
//                    .doctorId(2)
//                    .userId(3)
//                    .drugs(List.of(
//                            DrugRecipeDto.builder().name("MARIHYANA").doze(0.5f).build(),
//                            DrugRecipeDto.builder().name("ASPERIN").doze(0.2f).build(),
//                            DrugRecipeDto.builder().name("KRAHMAL KARTOSHKI").doze(3f).build()
//                    ))
//                    .instruction("Snimat' plenki s glaz")
//                    .build();
//            requestContext.addAttribute("consultation", consultationDto);
//            return CommandResult.forward(Page.CONSULTATION_PAGE);
//        }

        String consultationIdString = ParameterExtractor.extractString(Parameter.CONSULTATION_ID, requestContext);
        int consultationId = Integer.parseInt(consultationIdString);

        requestContext.addAttribute(Attribute.CONSULTATION_ID, consultationId);

        Consultation consultation = consultationService.getConsultationById(consultationId);
        User doctor = userService.getUserById(consultation.getDoctorId());
        int userId = patientCardService.getPatientCardById(consultation.getPatientId()).getUserId();
        User user = userService.getUserById(userId);

        ConsultationDto.ConsultationDtoBuilder consultationDtoBuilder = ConsultationDto.builder()
                .communicationType(consultation.getCommunicationType().equals(CommunicationType.FACE_TO_FACE) ? "FACE TO FACE" : "ONLINE")
                .date(consultation.getDate())
                .duration(consultation.getDuration())
                .doctorFirstName(doctor.getFirstName())
                .doctorLastName(doctor.getLastName())
                .patientFirstName(user.getFirstName())
                .patientLastName(user.getLastName())
                .consultationStatus(consultation.getStatus());


        if (consultation.getStatus().equals(ConsultationStatus.COMPLETED)) {
            TreatmentCourse treatmentCourse = treatmentCourseService.getTreatmentCourseById(consultation.getTreatmentCourseId());
            List<DiseaseSymptom> diseaseSymptoms = treatmentCourseService.getDiseaseSymptoms(treatmentCourse.getTreatmentCourseId());
            List<DiseaseWithSymptomsDto> diseaseWithSymptomsDtos = new ArrayList<>();
            for (DiseaseSymptom diseaseSymptom : diseaseSymptoms) {
                int diseaseId = diseaseSymptom.getDiseaseId();
                Disease disease = diseaseService.getDiseaseById(diseaseId);
                DiseaseWithSymptomsDto diseaseWithSymptomsDto = DiseaseWithSymptomsDto.builder()
                        .name(disease.getName())
                        .id(diseaseId)
                        .symptoms(diseaseSymptom.getSymptoms())
                        .build();
                diseaseWithSymptomsDtos.add(diseaseWithSymptomsDto);
            }

            List<DrugRecipe> drugRecipes = treatmentCourseService.getDrugRecipes(treatmentCourse.getTreatmentCourseId());
            List<DrugRecipeDto> drugDtoWithDozes = new ArrayList<>();
            for (DrugRecipe drugRecipe : drugRecipes) {
                int drugId = drugRecipe.getDrugId();
                Drug drugById = drugService.getDrugById(drugId);
                String name = drugById.getName();
                DrugRecipeDto drugDtoWithDoze = DrugRecipeDto.builder().
                        name(name)
                        .doze(drugRecipe.getDose())
                        .description(drugRecipe.getDescription())
                        .build();
                drugDtoWithDozes.add(drugDtoWithDoze);
            }
            consultationDtoBuilder
                    .diseases(diseaseWithSymptomsDtos)
                    .drugs(drugDtoWithDozes)
                    .price(consultation.getPrice())

                    .instruction(treatmentCourse.getInstruction());
        }
        requestContext.addAttribute("consultation", consultationDtoBuilder.build());
        return CommandResult.forward(Page.CONSULTATION_PAGE);
    }
}
