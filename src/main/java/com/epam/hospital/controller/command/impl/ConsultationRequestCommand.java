package com.epam.hospital.controller.command.impl;

import com.epam.hospital.controller.command.Command;
import com.epam.hospital.controller.command.CommandResult;
import com.epam.hospital.controller.command.util.ParameterExtractor;
import com.epam.hospital.controller.constant.CommandName;
import com.epam.hospital.controller.constant.Page;
import com.epam.hospital.controller.request.RequestContext;
import com.epam.hospital.model.treatment.Consultation;
import com.epam.hospital.model.treatment.type.CommunicationType;
import com.epam.hospital.service.database.ConsultationService;
import com.epam.hospital.service.database.impl.ConsultationServiceImpl;
import com.epam.hospital.service.exception.ServiceException;
import com.epam.hospital.util.constant.Attribute;
import jakarta.servlet.http.HttpSession;

public class ConsultationRequestCommand implements Command {
    private static final String CONSULTATION_WAITING_STATUS = "WAITING";
    private static final int CONSULTATION_DEFAULT_DURATION = 45;
    private static final double CONSULTATION_DEFAULT_PRICE = 130;
    private static final String CONSULTATION_REQUEST_PAGE_COMMAND = "MentalHospital?command=" + CommandName.CONSULTATION_REQUEST_PAGE;

    private static final ConsultationService consultationService = ConsultationServiceImpl.getInstance();
    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        int patientCardId = Integer.parseInt(requestContext.getSessionAttribute(Attribute.PATIENT_CARD_ID).toString());
        //проверить
        String communicationTypeStr = ParameterExtractor.extractString(Attribute.COMMUNICATION_TYPE, requestContext);
        CommunicationType communicationType = CommunicationType.valueOf(communicationTypeStr);

        Consultation consultation = new Consultation().builder()
                .communicationType(communicationType)
                .date(ParameterExtractor.extractDate(Attribute.DATE, requestContext))
                .doctorId(ParameterExtractor.extractInt(Attribute.DOCTOR_ID, requestContext))
                .patientId(patientCardId)
                .build();
        consultationService.save(consultation);
        return CommandResult.redirect(CONSULTATION_REQUEST_PAGE_COMMAND);
    }
}
