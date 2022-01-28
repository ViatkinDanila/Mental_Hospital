package com.epam.hospital.controller.command.impl;

import com.epam.hospital.controller.command.Command;
import com.epam.hospital.controller.command.CommandResult;
import com.epam.hospital.controller.command.util.ParameterExtractor;
import com.epam.hospital.controller.constant.CommandName;
import com.epam.hospital.controller.constant.Page;
import com.epam.hospital.controller.request.RequestContext;
import com.epam.hospital.model.treatment.Consultation;
import com.epam.hospital.model.treatment.type.CommunicationType;
import com.epam.hospital.model.treatment.type.ConsultationStatus;
import com.epam.hospital.service.database.ConsultationService;
import com.epam.hospital.service.database.impl.ConsultationServiceImpl;
import com.epam.hospital.service.exception.ServiceException;
import com.epam.hospital.util.constant.Attribute;
import jakarta.servlet.http.HttpSession;

import java.sql.Date;

public class ConsultationRequestCommand implements Command {
    private static final String CONSULTATION_PENDING_STATUS = "PENDING";
    private static final int WAITING_TREATMENT_COURSE_ID = 1;
    private static final String CONSULTATION_REQUEST_PAGE_COMMAND = "MentalHospital?command=" + CommandName.CONSULTATION_REQUEST_PAGE;
    private static final ConsultationService consultationService = ConsultationServiceImpl.getInstance();

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        //
        //patientCardIdStr можно брать из сессии
        //
        String patentCardIdStr = ParameterExtractor.extractString(Attribute.PATIENT_CARD_ID, requestContext);
        int patientCardId = Integer.parseInt(patentCardIdStr);
        //проверить
        String communicationTypeStr = ParameterExtractor.extractString(Attribute.COMMUNICATION_TYPE, requestContext);
        CommunicationType communicationType = CommunicationType.valueOf(communicationTypeStr);

        Consultation consultation = new Consultation().builder()
                .communicationType(communicationType)
                .date(ParameterExtractor.extractDate(Attribute.DATE, requestContext))
                .doctorId(ParameterExtractor.extractInt(Attribute.DOCTOR_ID, requestContext))
                .status(ConsultationStatus.valueOf(CONSULTATION_PENDING_STATUS))
                .patientId(patientCardId)
                .treatmentCourseId(WAITING_TREATMENT_COURSE_ID)
                .build();
        consultationService.save(consultation);
        return CommandResult.redirect(CONSULTATION_REQUEST_PAGE_COMMAND);
    }
}
