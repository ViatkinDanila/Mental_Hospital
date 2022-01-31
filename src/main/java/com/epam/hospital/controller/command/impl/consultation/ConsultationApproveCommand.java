package com.epam.hospital.controller.command.impl.consultation;

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
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConsultationApproveCommand implements Command {
    private static final ConsultationService consultationService = ConsultationServiceImpl.getInstance();
    private static final String CONSULTATION_PAGE_COMMAND = "MentalHospital?command=" + CommandName.CONSULTATION_PAGE ;
    private static final String CONSULTATION_STATUS_APPROVED = "APPROVED";

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        int consultationId = ParameterExtractor.extractInt(Parameter.CONSULTATION_ID, requestContext);
        Consultation consultation = consultationService.getConsultationById(consultationId);
        consultation.setTreatmentCourseId(null);
//        int price = ParameterExtractor.extractInt(Parameter.PRICE, requestContext);
        ConsultationStatus consultationStatus = ConsultationStatus.valueOf(CONSULTATION_STATUS_APPROVED);

        consultation.setStatus(consultationStatus);
//        consultation.setPrice(price);
        consultationService.update(consultation);
        return CommandResult.redirect(CONSULTATION_PAGE_COMMAND + "&id=" + consultationId);
    }
}
