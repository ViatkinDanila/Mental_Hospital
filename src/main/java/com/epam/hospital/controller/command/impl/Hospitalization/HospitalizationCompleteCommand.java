package com.epam.hospital.controller.command.impl.Hospitalization;

import com.epam.hospital.constant.web.RequestParameters;
import com.epam.hospital.controller.command.Command;
import com.epam.hospital.controller.command.CommandResult;
import com.epam.hospital.controller.command.util.ParameterExtractor;
import com.epam.hospital.constant.web.CommandName;
import com.epam.hospital.controller.request.RequestContext;
import com.epam.hospital.model.treatment.ChamberStaying;
import com.epam.hospital.service.database.ChamberService;
import com.epam.hospital.service.database.impl.ChamberServiceImpl;
import com.epam.hospital.service.exception.ServiceException;

public class HospitalizationCompleteCommand implements Command {
    private static final String PROFILE_PAGE_COMMAND = "MentalHospital?command=" + CommandName.PROFILE_PAGE;

    ChamberService chamberService = new ChamberServiceImpl();
    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        int hospitalizationId = ParameterExtractor.extractInt(RequestParameters.HOSPITALIZATION_ID, requestContext);
        int chamberId = ParameterExtractor.extractInt(RequestParameters.CHAMBER_ID, requestContext);
        ChamberStaying chamberStaying = chamberService.getChamberStayingById(chamberId, hospitalizationId);
        chamberStaying.setDateOut(ParameterExtractor.extractDate(RequestParameters.DATE_OUT, requestContext));
        chamberService.updateChamberStaying(chamberStaying);
        return CommandResult.redirect(PROFILE_PAGE_COMMAND);
    }
}
