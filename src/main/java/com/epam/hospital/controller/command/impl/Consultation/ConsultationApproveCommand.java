package com.epam.hospital.controller.command.impl.Consultation;

import com.epam.hospital.controller.command.Command;
import com.epam.hospital.controller.command.CommandResult;
import com.epam.hospital.controller.command.util.ParameterExtractor;
import com.epam.hospital.controller.constant.CommandName;
import com.epam.hospital.controller.request.RequestContext;
import com.epam.hospital.model.treatment.Consultation;
import com.epam.hospital.model.treatment.type.CommunicationType;
import com.epam.hospital.model.treatment.type.ConsultationStatus;
import com.epam.hospital.service.database.ConsultationService;
import com.epam.hospital.service.database.impl.ConsultationServiceImpl;
import com.epam.hospital.service.exception.ServiceException;
import com.epam.hospital.util.constant.Parameter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConsultationApproveCommand implements Command {
    private static final ConsultationService consultationService = ConsultationServiceImpl.getInstance();
    private static final String CONSULTATION_PAGE_COMMAND = "MentalHospital?command=" + CommandName.CONSULTATION_PAGE ;
    private static final String CONSULTATION_STATUS_APPROVED = "APPROVED";

    private static final int DEFAULT_PRICE = 10;

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        int consultationId = ParameterExtractor.extractInt(Parameter.CONSULTATION_ID, requestContext);
        Consultation consultation = consultationService.getConsultationById(consultationId);
        consultation.setTreatmentCourseId(null);
        ConsultationStatus consultationStatus = ConsultationStatus.valueOf(CONSULTATION_STATUS_APPROVED);

        consultation.setStatus(consultationStatus);
        consultation.setPrice(consultation.getCommunicationType().equals(CommunicationType.FACE_TO_FACE) ? DEFAULT_PRICE : 0);
        consultationService.update(consultation);
        return CommandResult.redirect(CONSULTATION_PAGE_COMMAND + "&id=" + consultationId);
    }
}
