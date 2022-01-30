package com.epam.hospital.controller.command.impl.Consultation;

import com.epam.hospital.controller.command.Command;
import com.epam.hospital.controller.command.CommandResult;
import com.epam.hospital.controller.command.util.ParameterExtractor;
import com.epam.hospital.controller.constant.CommandName;
import com.epam.hospital.controller.request.RequestContext;
import com.epam.hospital.model.treatment.Consultation;
import com.epam.hospital.model.treatment.type.ConsultationStatus;
import com.epam.hospital.service.database.ConsultationService;
import com.epam.hospital.service.database.impl.ConsultationServiceImpl;
import com.epam.hospital.service.exception.ServiceException;
import com.epam.hospital.util.constant.Parameter;

public class ConsultationRejectCommand implements Command {
    private static final ConsultationService consultationService = ConsultationServiceImpl.getInstance();
    private static final String PROFILE_PAGE_COMMAND = "MentalHospital?command=" + CommandName.PROFILE_PAGE;

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        int consultationId = ParameterExtractor.extractInt(Parameter.CONSULTATION_ID, requestContext);
        Consultation consultation = consultationService.getConsultationById(consultationId);;
        consultation.setStatus(ConsultationStatus.REJECTED);
        consultationService.update(consultation);
        return CommandResult.redirect(PROFILE_PAGE_COMMAND);
    }
}
