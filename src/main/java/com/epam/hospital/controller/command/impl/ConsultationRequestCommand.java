package com.epam.hospital.controller.command.impl;

import com.epam.hospital.controller.command.Command;
import com.epam.hospital.controller.command.CommandResult;
import com.epam.hospital.controller.command.util.ParameterExtractor;
import com.epam.hospital.controller.request.RequestContext;
import com.epam.hospital.model.treatment.Consultation;
import com.epam.hospital.model.treatment.type.CommunicationType;
import com.epam.hospital.service.exception.ServiceException;
import com.epam.hospital.util.constant.Attribute;
import jakarta.servlet.http.HttpSession;

public class ConsultationRequestCommand implements Command {
    private static final String CONSULTATION_WAITING_STATUS = "WAITING";
    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        int patientCardId = ParameterExtractor.extractInt(Attribute.PATIENT_CARD_ID, requestContext);
        //проверить
        CommunicationType communicationType = CommunicationType.valueOf(ParameterExtractor.extractString(Attribute.COMMUNICATION_TYPE, requestContext));

        Consultation consultation = new Consultation().builder()
                .communicationType(communicationType)
                .date(ParameterExtractor.extractDate(Attribute.DATE, requestContext))
                .doctorId(ParameterExtractor.extractInt(Attribute.DOCTOR_ID, requestContext))
                .patientId(patientCardId)
                .duration(45)//в зависимости от типа консультации, сделать сервис
                .price(1)//в зависимости от типа консультации, сделать сервис
                .build();

        return null;
    }
}
