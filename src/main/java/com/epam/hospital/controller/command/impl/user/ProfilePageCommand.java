package com.epam.hospital.controller.command.impl.user;

import com.epam.hospital.controller.command.Command;
import com.epam.hospital.controller.command.CommandResult;
import com.epam.hospital.controller.constant.Attribute;
import com.epam.hospital.controller.constant.Page;
import com.epam.hospital.controller.request.RequestContext;
import com.epam.hospital.model.dto.DoctorInfoDto;
import com.epam.hospital.model.dto.PatientInfoDto;
import com.epam.hospital.model.dto.ShortConsultationDto;
import com.epam.hospital.model.dto.UserInfoDto;
import com.epam.hospital.model.treatment.Consultation;
import com.epam.hospital.model.treatment.PatientCard;
import com.epam.hospital.model.treatment.type.ConsultationStatus;
import com.epam.hospital.model.user.User;
import com.epam.hospital.model.user.info.DoctorInfo;
import com.epam.hospital.service.database.ConsultationService;
import com.epam.hospital.service.database.PatientCardService;
import com.epam.hospital.service.database.UserService;
import com.epam.hospital.service.database.impl.ConsultationServiceImpl;
import com.epam.hospital.service.database.impl.PatientCardServiceImpl;
import com.epam.hospital.service.database.impl.UserServiceImpl;
import com.epam.hospital.service.exception.ServiceException;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProfilePageCommand implements Command {
    private static final UserService userService = UserServiceImpl.getInstance();
    private static final PatientCardService patientCardService = PatientCardServiceImpl.getInstance();
    private static final ConsultationService consultationService = ConsultationServiceImpl.getInstance();

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        int userId = (int) requestContext.getSessionAttribute(Attribute.USER_ID);
        String role = (String) requestContext.getSessionAttribute(Attribute.ROLE);
        User user = userService.getUserById(userId);

        UserInfoDto userInfoDto = UserInfoDto.builder()
                .fullName(user.getFirstName() + " " + user.getLastName())
                .role(role)
                .status(user.isBanned() ? "BANNED" : "ACTIVE")
                .email(user.getEmail())
                .build();
        requestContext.addAttribute(Attribute.USER_INFO, userInfoDto);

        List<Consultation> consultations;
        if (role.equalsIgnoreCase("USER")) {
            int patientCardId = (int) requestContext.getSessionAttribute(Attribute.PATIENT_CARD_ID);
            PatientCard patientCard = patientCardService.getPatientCardById(patientCardId);
            PatientInfoDto patientInfoDto = PatientInfoDto.builder()
                    .age(patientCard.getAge())
                    .spareNumber(patientCard.getSpareNumber())
                    .build();
            requestContext.addAttribute(Attribute.PATIENT_INFO, patientInfoDto);

            consultations = consultationService.getAllConsultationsByPatientCardId(patientCard.getCardId());
        } else {
            DoctorInfo doctorInfo = userService.getDoctorInfoById(userId);
            DoctorInfoDto doctorInfoDto = DoctorInfoDto.builder()
                    .specialization(doctorInfo.getSpecialization())
                    .classification(doctorInfo.getClassification())
                    .workExperience(doctorInfo.getWorkExperience())
                    .build();

            requestContext.addAttribute(Attribute.DOCTOR_INFO, doctorInfoDto);
            consultations = consultationService.getAllConsultationsByDoctorId(userId);
        }

        List<ShortConsultationDto> consultationDtoList = new ArrayList<>();
        for (Consultation consultation : consultations) {
            ShortConsultationDto.ShortConsultationDtoBuilder shortConsultationDtoBuilder = ShortConsultationDto.builder()
                    .date(consultation.getDate())
                    .communicationType(consultation.getCommunicationType().toString())
                    .consultationStatus(consultation.getStatus())
                    .id(consultation.getConsultationId());
            if (role.equalsIgnoreCase("USER")) {
                User doctor = userService.getUserById(consultation.getDoctorId());
                shortConsultationDtoBuilder.doctorFullName(doctor.getFirstName() + " " + doctor.getLastName());
            } else {
                PatientCard patientCard = patientCardService.getPatientCardById(consultation.getPatientId());
                User patient = userService.getUserById(patientCard.getUserId());
                shortConsultationDtoBuilder.patientFullName(patient.getFirstName() + " " + patient.getLastName());
            }
            consultationDtoList.add(shortConsultationDtoBuilder.build());
        }
        requestContext.addAttribute(Attribute.CONSULTATIONS, consultationDtoList);

        return CommandResult.forward(Page.PROFILE_PAGE);
    }
}
