package com.epam.hospital.controller.command.impl.doctor;

import com.epam.hospital.constant.web.RequestParameters;
import com.epam.hospital.controller.command.Command;
import com.epam.hospital.controller.command.CommandResult;
import com.epam.hospital.controller.command.util.ParameterExtractor;
import com.epam.hospital.constant.web.CommandName;
import com.epam.hospital.controller.request.RequestContext;
import com.epam.hospital.model.treatment.Hospitalization;
import com.epam.hospital.model.treatment.type.HospitalizationStatus;
import com.epam.hospital.service.logic.HospitalizationService;
import com.epam.hospital.service.logic.impl.HospitalizationServiceImpl;
import com.epam.hospital.service.exception.ServiceException;

public class HospitalizationRejectCommand implements Command {
    HospitalizationService hospitalizationService = new HospitalizationServiceImpl();
    private static final String HOSPITALIZATION_PAGE_COMMAND = "MentalHospital?command=" + CommandName.HOSPITALIZATION_PAGE ;

    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        int hospitalizationId = ParameterExtractor.extractInt(RequestParameters.HOSPITALIZATION_ID, requestContext);
        Hospitalization hospitalization = hospitalizationService.getHospitalizationById(hospitalizationId);
        hospitalization.setStatus(HospitalizationStatus.REJECTED);
        hospitalizationService.update(hospitalization);
        return CommandResult.redirect(HOSPITALIZATION_PAGE_COMMAND);
    }
}
