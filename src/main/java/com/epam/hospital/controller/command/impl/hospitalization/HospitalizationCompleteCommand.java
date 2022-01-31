package com.epam.hospital.controller.command.impl.hospitalization;

import com.epam.hospital.controller.command.Command;
import com.epam.hospital.controller.command.CommandResult;
import com.epam.hospital.controller.command.util.ParameterExtractor;
import com.epam.hospital.controller.constant.CommandName;
import com.epam.hospital.controller.request.RequestContext;
import com.epam.hospital.model.treatment.ChamberStaying;
import com.epam.hospital.service.database.ChamberService;
import com.epam.hospital.service.database.impl.ChamberServiceImpl;
import com.epam.hospital.service.exception.ServiceException;
import com.epam.hospital.util.constant.Parameter;

public class HospitalizationCompleteCommand implements Command {
    private static final String PROFILE_PAGE_COMMAND = "MentalHospital?command=" + CommandName.PROFILE_PAGE;

    ChamberService chamberService = new ChamberServiceImpl();
    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        int hospitalizationId = ParameterExtractor.extractInt(Parameter.HOSPITALIZATION_ID, requestContext);
        int chamberId = ParameterExtractor.extractInt(Parameter.CHAMBER_ID, requestContext);
        ChamberStaying chamberStaying = chamberService.getChamberStayingById(chamberId, hospitalizationId);
        chamberStaying.setDateOut(ParameterExtractor.extractDate(Parameter.DATE_OUT, requestContext));
        chamberService.updateChamberStaying(chamberStaying);
        return CommandResult.redirect(PROFILE_PAGE_COMMAND);
    }
}
