package com.epam.hospital.controller.command.impl.doctor.Hospitalization;

import com.epam.hospital.constant.web.RequestParameters;
import com.epam.hospital.controller.command.Command;
import com.epam.hospital.controller.command.CommandResult;
import com.epam.hospital.controller.command.util.ParameterExtractor;
import com.epam.hospital.constant.web.CommandName;
import com.epam.hospital.controller.request.RequestContext;
import com.epam.hospital.model.treatment.ChamberStaying;
import com.epam.hospital.model.treatment.Hospitalization;
import com.epam.hospital.model.treatment.type.HospitalizationStatus;
import com.epam.hospital.service.logic.ChamberService;
import com.epam.hospital.service.logic.HospitalizationService;
import com.epam.hospital.service.logic.impl.ChamberServiceImpl;
import com.epam.hospital.service.logic.impl.HospitalizationServiceImpl;
import com.epam.hospital.service.exception.ServiceException;

public class HospitalizationCompleteCommand implements Command {
    private static final String HOSPITALIZATION_PAGE_COMMAND = "MentalHospital?command=" + CommandName.HOSPITALIZATION_PAGE ;

    private static final ChamberService chamberService = ChamberServiceImpl.getInstance();
    private static final HospitalizationService hospitalizationService = HospitalizationServiceImpl.getInstance();
    @Override
    public CommandResult execute(RequestContext requestContext) throws ServiceException {
        int hospitalizationId = ParameterExtractor.extractInt(RequestParameters.HOSPITALIZATION_ID, requestContext);
        Hospitalization hospitalization = hospitalizationService.getHospitalizationById(hospitalizationId);
        hospitalization.setStatus(HospitalizationStatus.COMPLETED);
        ChamberStaying chamberStaying = chamberService.getChamberStayingById(hospitalizationId);
        chamberStaying.setDateOut(ParameterExtractor.extractDate(RequestParameters.DATE_OUT, requestContext));
        chamberStaying.setPrice(ParameterExtractor.extractDouble(RequestParameters.PRICE, requestContext));

        hospitalizationService.update(hospitalization);
        chamberService.updateChamberStaying(chamberStaying);
        return CommandResult.redirect(HOSPITALIZATION_PAGE_COMMAND + "&id=" + hospitalizationId);
    }
}
